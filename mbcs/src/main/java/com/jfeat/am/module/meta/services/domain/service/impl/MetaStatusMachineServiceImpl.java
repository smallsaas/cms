package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.meta.constant.StatusConstant;
import com.jfeat.am.module.meta.services.domain.dao.QueryMetaStatusMachineDao;
import com.jfeat.am.module.meta.services.domain.model.AdditionModel;
import com.jfeat.am.module.meta.services.domain.model.BulkApprovalModel;
import com.jfeat.am.module.meta.services.domain.model.BulkChangStatusModel;
import com.jfeat.am.module.meta.services.domain.model.BulkChangeStatusWithVersionModel;
import com.jfeat.am.module.meta.services.domain.model.ChangeStatusModel;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentStatus;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentVersionAndStatus;
import com.jfeat.am.module.meta.services.domain.service.MetaStatusMachineService;
import com.jfeat.am.module.meta.services.domain.service.MetaWorkflowLiteActivityService;
import com.jfeat.am.module.meta.services.domain.utils.MetaUtils;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaStatusMachineServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaStatusMachineService")
@CacheConfig(cacheNames = "CONSTANT")
public class MetaStatusMachineServiceImpl extends CRUDMetaStatusMachineServiceImpl
        implements MetaStatusMachineService {

    @Resource
    private MetaWorkflowLiteActivityService metaWorkflowLiteActivityService;

    @Resource
    private QueryMetaStatusMachineDao queryMetaStatusMachineDao;

    @Override
    public List<MetaStatusMachine> findMetaStatusMachine(MetaStatusMachine queryEntity) {
        return queryMetaStatusMachineDao.findMetaStatusMachine(queryEntity);
    }

    @Override
    public List<MetaStatusMachine> getLinkedEntityStatusList(String entity) {
        // 根据entity查询列表
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        Set<String> handledStatus = new HashSet<>();
        handledStatus.add(StatusConstant.CANCEL);
        MetaStatusMachine startMeta = getLinkedByFromStatus(StatusConstant.START, handledStatus, metaList);
        String fromStatus = startMeta.getToStatus();
        List<MetaStatusMachine> linkedList = new ArrayList<>();
        while (true) {
            MetaStatusMachine next = getLinkedByFromStatus(fromStatus, handledStatus, metaList);
            linkedList.add(next);
            // 如果状态流toStatus为“end”，跳出循环
            if (next.getToStatus().equals(StatusConstant.END)) {
                break;
            }
            handledStatus.add(next.getFromStatus());
            fromStatus = next.getToStatus();
        }
        return linkedList;
    }

    @Override
    @Transactional
    @CacheEvict(key = "'entity' + #metaStatusMachine.entity")
    public Integer addMetaStatus(MetaStatusMachine metaStatusMachine) {
        return createMaster(metaStatusMachine);
    }

    @Override
    @Transactional
    public Integer changeEntityStatus(String entity, Long id, ChangeStatusModel model) {
        if (null == model || StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]缺失参数：status");
        }
        String toStatus = model.getStatus();
        // 加载状态流
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity);
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        // 不允许状态跳转
        if (!canMatchMeta(entityCurrentStatus, toStatus, metaStatusMachineList)) {
            throw new BusinessException(BusinessCode.ErrorStatus.getCode(),
                    new StringBuilder()
                            .append("[Meta]不允许状态跳转：")
                            .append("From ").append(entityCurrentStatus)
                            .append(" To ").append(toStatus)
                            .toString());
        }
        // 更新实体
        return queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, toStatus);
    }

    @Override
    @Transactional
    public Integer pass(String entity, Long id, AdditionModel additionModel) {
        List<MetaStatusMachine> linkedMetaList = getLinkedEntityStatusList(entity);
        return doPass(entity, id, linkedMetaList, additionModel);
    }

    @Override
    @Transactional
    public Integer reject(String entity, Long id, AdditionModel additionModel) {
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        return doReject(entity, id, metaList, additionModel);
    }

    @Override
    @Transactional
    public Integer back(String entity, Long id, AdditionModel additionModel) {
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        List<MetaStatusMachine> linkedMetaList = getLinkedEntityStatusList(entity);
        return doBack(entity, id, metaList, linkedMetaList, additionModel);
    }

    @Override
    @Transactional
    public Integer cancel(String entity, Long id, AdditionModel additionModel) {
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        return doCancel(entity, id, metaList, additionModel);
    }

    @Override
    @Transactional
    public BulkResult bulkChangeEntityStatus(String entity, BulkChangStatusModel model) {
        // 参数校验
        if (null == model || null == model.getIds() || model.getIds().isEmpty()) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        if (StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，status不能为空");
        }
        List<Long> ids = model.getIds();
        String status = model.getStatus();
        int forbiddenCount = 0;

        // 获取可以到达该状态的状态机
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity);
        // 获取表名
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        List<EntityCurrentStatus> entityCurrentStatuses =
                queryMetaStatusMachineDao.getEntitiesCurrentStatus(ids, entityTableName);
        // 可以更新的id列表
        List<Long> canUpdateIds = new ArrayList<>();
        for (EntityCurrentStatus entityCurrentStatus : entityCurrentStatuses) {
            if (!canMatchMeta(entityCurrentStatus.getStatus(), status, metaStatusMachineList)) {
                forbiddenCount++;
                continue;
            }
            canUpdateIds.add(entityCurrentStatus.getId());
        }
        // 成功个数
        int successCount = 0;
        // 失败个数
        int failCount = 0;
        if (!CollectionUtils.isEmpty(canUpdateIds)) {
            successCount = queryMetaStatusMachineDao.batchUpdateEntityStatus(entityTableName, canUpdateIds, status);
            failCount = canUpdateIds.size() - successCount;
        }
        // 构建返回
        return MetaUtils.createBulkResult(
                successCount > 0 ? new BulkMessage(200, successCount, "[Meta]更改状态成功") : null,
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]更新失败，数据库错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]不允许更新，不允许跳转到状态:["+status+"]") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkChangeEntityStatus(String entity, BulkChangeStatusWithVersionModel model) {
        // 参数校验
        if (null == model || null == model.getIds() || model.getIds().isEmpty()) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        if (StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，status不能为空");
        }

        Map<Long, Integer> idVersions = new HashMap<>();
        model.getIds().forEach(idVersion -> idVersions.put(idVersion.getId(), idVersion.getVersion()));
        String status = model.getStatus();
        int forbiddenCount = 0;
        int statusErrorCount = 0;

        // 获取可以到达该状态的状态机
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity);
        // 获取表名
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        List<EntityCurrentVersionAndStatus> entityCurrentVersionAndStatus =
                queryMetaStatusMachineDao.getEntityCurrentVersionAndStatus(new ArrayList<>(idVersions.keySet()), entityTableName);
        // 可以更新的id列表
        List<Long> canUpdateIds = new ArrayList<>();
        for (EntityCurrentVersionAndStatus row : entityCurrentVersionAndStatus) {
            if (!canMatchMeta(row.getStatus(), status, metaStatusMachineList)) {
                forbiddenCount++;
                continue;
            }
            if (idVersions.get(row.getId()).intValue() != row.getVersion().intValue()) {
                statusErrorCount++;
                continue;
            }
            canUpdateIds.add(row.getId());
        }
        // 成功个数
        int successCount = 0;
        // 失败个数
        int failCount = 0;
        if (!CollectionUtils.isEmpty(canUpdateIds)) {
            successCount = queryMetaStatusMachineDao.batchUpdateEntityStatus(entityTableName, canUpdateIds, status);
            failCount = canUpdateIds.size() - successCount;
        }
        // 构建返回
        return MetaUtils.createBulkResult(
                successCount > 0 ? new BulkMessage(200, successCount, "[Meta]更改状态成功") : null,
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]更新失败，数据库错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]不允许更新，不允许跳转到状态:["+status+"]") : null,
                statusErrorCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), statusErrorCount,
                        "[Meta]数据版本不一致") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkPass(String entity, BulkApprovalModel bulkApprovalModel) {
        if (null == bulkApprovalModel || CollectionUtils.isEmpty(bulkApprovalModel.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        int successCount = 0;
        int failCount = 0;
        int forbiddenCount = 0;
        List<MetaStatusMachine> linkedMetaList = getLinkedEntityStatusList(entity);
        for (Long id : bulkApprovalModel.getIds()) {
            try {
                // 执行通过操作
                int result = doPass(entity, id, linkedMetaList, bulkApprovalModel.getAddition());
                if (result > 0) {
                    successCount++;
                } else {
                    failCount++;
                }
            } catch (BusinessException e) {
                forbiddenCount++;
            }
        }
        // 构建返回
        return MetaUtils.createBulkResult(new BulkMessage(200, successCount, "[Meta]操作成功"),
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]操作失败，数据库更新错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]操作被禁止，状态不允许跳转或配置缺失") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkReject(String entity, BulkApprovalModel bulkApprovalModel) {
        if (null == bulkApprovalModel || CollectionUtils.isEmpty(bulkApprovalModel.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        int successCount = 0;
        int failCount = 0;
        int forbiddenCount = 0;
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        for (Long id : bulkApprovalModel.getIds()) {
            try {
                // 执行通过操作
                int result = doReject(entity, id, metaList, bulkApprovalModel.getAddition());
                if (result > 0) {
                    successCount++;
                } else {
                    failCount++;
                }
            } catch (BusinessException e) {
                forbiddenCount++;
            }
        }
        // 构建返回
        return MetaUtils.createBulkResult(new BulkMessage(200, successCount, "[Meta]操作成功"),
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]操作失败，数据库更新错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]操作被禁止，状态不允许跳转或配置缺失") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkBack(String entity, BulkApprovalModel bulkApprovalModel) {
        if (null == bulkApprovalModel || CollectionUtils.isEmpty(bulkApprovalModel.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        int successCount = 0;
        int failCount = 0;
        int forbiddenCount = 0;
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        List<MetaStatusMachine> linkedMetaList = getLinkedEntityStatusList(entity);
        for (Long id : bulkApprovalModel.getIds()) {
            try {
                // 执行拒绝操作
                int result = doBack(entity, id, metaList, linkedMetaList, bulkApprovalModel.getAddition());
                if (result > 0) {
                    successCount++;
                } else {
                    failCount++;
                }
            } catch (BusinessException e) {
                forbiddenCount++;
            }
        }
        // 构建返回
        return MetaUtils.createBulkResult(new BulkMessage(200, successCount, "[Meta]操作成功"),
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]操作失败，数据库更新错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]操作被禁止，状态不允许跳转或配置缺失") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkCancel(String entity, BulkApprovalModel bulkApprovalModel) {
        if (null == bulkApprovalModel || CollectionUtils.isEmpty(bulkApprovalModel.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]参数缺失，ids不能为空");
        }
        int successCount = 0;
        int failCount = 0;
        int forbiddenCount = 0;
        List<MetaStatusMachine> metaList = getMetaStatusMachineList(entity);
        for (Long id : bulkApprovalModel.getIds()) {
            try {
                // 执行关闭操作
                int result = doCancel(entity, id, metaList, bulkApprovalModel.getAddition());
                if (result > 0) {
                    successCount++;
                } else {
                    failCount++;
                }
            } catch (BusinessException e) {
                forbiddenCount++;
            }
        }
        // 构建返回
        return MetaUtils.createBulkResult(new BulkMessage(200, successCount, "[Meta]操作成功"),
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]操作失败，数据库更新错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.ErrorStatus.getCode(), forbiddenCount,
                        "[Meta]操作被禁止，状态不允许跳转或配置缺失") : null);
    }


    /**
     * 获取可以更新的状态机
     * @param entity 实体
     * @return
     */
    @Cacheable(key = "#entity")
    public List<MetaStatusMachine> getMetaStatusMachineList(String entity) {
        MetaStatusMachine queryEntity = new MetaStatusMachine();
        queryEntity.setEntity(entity);
        List<MetaStatusMachine> metaStatusMachineList = queryMetaStatusMachineDao.findMetaStatusMachine(queryEntity);
        if (CollectionUtils.isEmpty(metaStatusMachineList)) {
            throw new BusinessException(BusinessCode.ErrorStatus.getCode(),
                    "[Meta]未配置状态流，请检查：entity=" + entity);
        }
        return metaStatusMachineList;
    }

    /**
     * 通过审批
     * @param entity
     * @param id
     * @param linkedMetaList
     * @param additionModel
     * @return
     */
    private int doPass(String entity, Long id, List<MetaStatusMachine> linkedMetaList,
                       AdditionModel additionModel) {
        String entityTableName = linkedMetaList.get(0).getEntityTableName();
        // 获取实体当前状态
        String fromStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        // 获取目标配置
        List<MetaStatusMachine> targetMetaList = linkedMetaList.stream()
                .filter(meta -> meta.getFromStatus().equals(fromStatus)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(targetMetaList)) {
            throw new BusinessException(BusinessCode.CodeBase,
                    "[Meta]通过审批操作失败，缺失配置，fromStatus="+fromStatus);
        }
        String toStatus = targetMetaList.get(0).getToStatus();
        // 更新实体
        int result = queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, toStatus);
        // 更新成功，保存工作流
        if (result > 0) {
            saveWorkflow(entity, id, fromStatus, toStatus, additionModel);
        }
        return result;
    }

    /**
     * 不通过审批
     * @param entity
     * @param id
     * @param metaList
     * @param additionModel
     * @return
     */
    private int doReject(String entity, Long id, List<MetaStatusMachine> metaList, AdditionModel additionModel) {
        String entityTableName = metaList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        return saveWorkflow(entity, id, entityCurrentStatus, entityCurrentStatus, additionModel);
    }

    /**
     * 回退
     * @param entity
     * @param id
     * @param metaList
     * @param linkedMetaList
     * @param additionModel
     * @return
     */
    private int doBack(String entity, Long id, List<MetaStatusMachine> metaList,
                       List<MetaStatusMachine> linkedMetaList, AdditionModel additionModel) {
        String entityTableName = metaList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        List<MetaStatusMachine> targetMetaList = metaList.stream()
                .filter(meta -> meta.getFromStatus().equals(entityCurrentStatus)
                        && !canMatchMeta(meta.getFromStatus(), meta.getToStatus(), linkedMetaList)
                        && !meta.getToStatus().equals(StatusConstant.END)
                        && !meta.getToStatus().equals(StatusConstant.CANCEL))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(targetMetaList)) {
            throw new BusinessException(BusinessCode.CodeBase,
                    "[Meta]不通过审批操作失败，缺失配置，fromStatus="+entityCurrentStatus);
        }
        if (targetMetaList.size() > 1) {
            // 获取toStatus List
            List<String> toStatusList =
                    targetMetaList.stream().map(MetaStatusMachine::getToStatus).collect(Collectors.toList());
            throw new BusinessException(BusinessCode.CodeBase.getCode(),
                    new StringBuilder("[Meta]获取到多个不通过审批状态：").append(toStatusList)
                            .append("，fromStatus=").append(entityCurrentStatus).toString());
        }
        String toStatus = targetMetaList.get(0).getToStatus();
        // 更新实体
        int result = queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, toStatus);
        // 更新成功，保存工作流
        if (result > 0) {
            saveWorkflow(entity, id, entityCurrentStatus, toStatus, additionModel);
        }
        return result;
    }

    /**
     * 关闭
     * @param entity
     * @param id
     * @param metaList
     * @param additionModel
     * @return
     */
    private int doCancel(String entity, Long id, List<MetaStatusMachine> metaList, AdditionModel additionModel) {
        String entityTableName = metaList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        if (!canMatchMeta(entityCurrentStatus, StatusConstant.CANCEL, metaList)) {
            throw new BusinessException(BusinessCode.ErrorStatus.getCode(),
                    "[Meta]关闭操作失败，未配置状态跳转：fromStatus="+entityCurrentStatus);
        }
        // 更新实体
        int result = queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, StatusConstant.CANCEL);
        // 更新成功，保存工作流
        if (result > 0) {
            saveWorkflow(entity, id, entityCurrentStatus, StatusConstant.CANCEL, additionModel);
        }
        return result;
    }

    /**
     * 根据fromStatus从列表中获取下一个未被处理的meta配置
     * @param fromStatus
     * @param handledStatus
     * @param metaList
     * @return
     */
    private MetaStatusMachine getLinkedByFromStatus(String fromStatus, Set<String> handledStatus,
                                                    List<MetaStatusMachine> metaList) {
        List<MetaStatusMachine> targetList =
                metaList.stream()
                        .filter(meta -> meta.getFromStatus().equals(fromStatus)
                                && !handledStatus.contains(meta.getToStatus()))
                        .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(targetList)) {
            throw new BusinessException(
                    BusinessCode.CodeBase.getCode(),
                    "[Meta]构建链式状态流失败：缺失配置，未找到对应的toStatus，fromStatus="+fromStatus);
        }
        if (targetList.size() > 1) {
            // 获取非退回toStatus List
            List<String> toStatusList =
                    targetList.stream().map(MetaStatusMachine::getToStatus).collect(Collectors.toList());
            throw new BusinessException(BusinessCode.CodeBase.getCode(),
                    new StringBuilder("[Meta]构建链式状态流失败：获取到多个toStatus=").append(toStatusList)
                            .append("，fromStatus=").append(fromStatus).toString());
        }
        return targetList.get(0);
    }

    /**
     * 能匹配到配置
     * @param fromStatus from status
     * @param toStatus to status
     * @param metaStatusMachineList 状态配置列表
     * @return
     */
    private boolean canMatchMeta(String fromStatus, String toStatus,
                                 List<MetaStatusMachine> metaStatusMachineList) {
        for (MetaStatusMachine e : metaStatusMachineList) {
            // 判断能不能更新
            if (e.getFromStatus().equals(fromStatus) && e.getToStatus().equals(toStatus)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 保存操作到工作流
     * @param entity 实体
     * @param entityId 实体id
     * @param fromStatus from status
     * @param toStatus to status
     * @param additionModel 附加信息
     * @return
     */
    private int saveWorkflow(String entity, Long entityId, String fromStatus, String toStatus,
                             AdditionModel additionModel) {
        MetaWorkflowLiteActivity activity = new MetaWorkflowLiteActivity();
        // 设置实体
        activity.setEntity(entity);
        // 设置实体id
        activity.setEntityId(entityId);
        // 设置 from status
        activity.setFromStatus(fromStatus);
        // 设置 to status
        activity.setToStatus(toStatus);
        // 设置创建者id
        activity.setCreatedById(JWTKit.getUserId());
        // 设置创建者
        activity.setCreatedBy(JWTKit.getAccount());
        // 设置创建时间
        activity.setCreatedTime(new Date());
        // 有附加条件
        if (null != additionModel) {
            // 有意见、备注，保存
            if (StringUtils.isNotBlank(additionModel.getNote())) {
                activity.setNote(additionModel.getNote());
            }
        }
        int result = metaWorkflowLiteActivityService.createMaster(activity);
        if (result < 0) {
            throw new BusinessException(BusinessCode.DatabaseInsertError.getCode(),
                    "[Meta]保留工作流活动失败，entityId="+entityId);
        }
        return result;
    }
}