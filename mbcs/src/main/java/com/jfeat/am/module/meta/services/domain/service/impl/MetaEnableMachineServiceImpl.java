package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.module.meta.services.domain.dao.QueryMetaEnableMachineDao;
import com.jfeat.am.module.meta.services.domain.service.MetaEnableMachineService;
import com.jfeat.am.module.meta.services.domain.utils.MetaUtils;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaEnableMachineServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEnableMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaEnableMachineService")
public class MetaEnableMachineServiceImpl extends CRUDMetaEnableMachineServiceImpl implements MetaEnableMachineService {

    @Resource
    private QueryMetaEnableMachineDao queryMetaEnableMachineDao;

    @Override
    public List<MetaEnableMachine> findMetaEnableMachine(MetaEnableMachine queryEntity) {
        return queryMetaEnableMachineDao.findMetaEnableMachine(queryEntity);
    }

    @Override
    @Transactional
    public Integer enableEntity(Long id, String entity) {
        return enableValidate(id, entity, true);
    }

    @Override
    @Transactional
    public Integer disableEntity(Long id, String entity) {
        return enableValidate(id, entity, false);
    }

    @Override
    @Transactional
    public Integer validateEntity(Long id, String entity, Integer value) {
        MetaEnableMachine metaEnableMachine = getMetaEnableMachine(entity);
        int min = metaEnableMachine.getRangeMin();
        int max = metaEnableMachine.getRangeMax();
        // 更新数值不在范围内
        if (value < min || value > max) {
            throw new BusinessException(
                    BusinessCode.BadRequest.getCode(), "value值不在可更新范围内，可更新范围："+min+"-"+max);
        }
        return update(metaEnableMachine, value, id);
    }

    @Override
    @Transactional
    public BulkResult bulkEnableEntity(List<Long> ids, String entity) {
        int success = bulkEnableValidate(ids, entity, true);
        return createBulkResult(success, ids.size() - success);
    }

    @Override
    @Transactional
    public BulkResult bulkDisableEntity(List<Long> ids, String entity) {
        int success = bulkEnableValidate(ids, entity, false);
        return createBulkResult(success, ids.size() - success);
    }

    @Override
    @Transactional
    public BulkResult bulkValidateEntity(List<Long> ids, String entity, Integer value) {
        MetaEnableMachine metaEnableMachine = getMetaEnableMachine(entity);
        int min = metaEnableMachine.getRangeMin();
        int max = metaEnableMachine.getRangeMax();
        // 更新数值不在范围内
        if (value < min || value > max) {
            throw new BusinessException(
                    BusinessCode.BadRequest.getCode(), "value值不在可更新范围内，可更新范围："+min+"-"+max);
        }
        int success = bulkUpdate(metaEnableMachine, value, ids);
        return createBulkResult(success, ids.size() - success);
    }

    /**
     * 判断实体value，并更新
     * @param id 实体id
     * @param entity 实体模块
     * @param isEnable 是否为启用（生效）
     * @return
     */
    private Integer enableValidate(Long id, String entity, boolean isEnable) {
        MetaEnableMachine metaEnableMachine = getMetaEnableMachine(entity);
        int value = 0;
        if (metaEnableMachine.getNegative() == 0 && isEnable || metaEnableMachine.getNegative() != 0 && !isEnable) {
            value = 1;
        }
        return update(metaEnableMachine, value, id);
    }

    /**
     * 判断实体value，并更新
     * @param ids 实体id列表
     * @param entity 实体模块
     * @param isEnable 是否为启用（生效）
     * @return
     */
    private Integer bulkEnableValidate(List<Long> ids, String entity, boolean isEnable) {
        MetaEnableMachine metaEnableMachine = getMetaEnableMachine(entity);
        int value = 0;
        if (metaEnableMachine.getNegative() == 0 && isEnable || metaEnableMachine.getNegative() != 0 && !isEnable) {
            value = 1;
        }
        return bulkUpdate(metaEnableMachine, value, ids);
    }

    /**
     * 获取有效机
     * @param entity
     * @return
     */
    private MetaEnableMachine getMetaEnableMachine(String entity) {
        // 查询参数
        MetaEnableMachine queryParams = new MetaEnableMachine();
        queryParams.setEntity(entity);
        // 查找目标配置
        MetaEnableMachine targetMeta = queryMetaEnableMachineDao.selectOne(queryParams);
        if (null == targetMeta) {
            throw new BusinessException(
                    BusinessCode.ErrorStatus.getCode(),
                    "获取meta enable配置失败，entity="+entity+"。请联系相关人员配置。");
        }
        return targetMeta;
    }

    /**
     * 更新实体有效位
     * @param metaEnableMachine
     * @param value 更新的有效位
     * @param id 实体id
     * @return
     */
    private Integer update(MetaEnableMachine metaEnableMachine, Integer value, Long id) {
        return queryMetaEnableMachineDao.validateEntity(
                metaEnableMachine.getEntityTableName(), metaEnableMachine.getEntityFieldName(), value, id);
    }

    /**
     * 批量更新实体有效位
     * @param metaEnableMachine
     * @param value 更新的有效位
     * @param ids id列表
     * @return
     */
    private Integer bulkUpdate(MetaEnableMachine metaEnableMachine, Integer value, List<Long> ids) {
        return queryMetaEnableMachineDao.bulkValidateEntity(
                metaEnableMachine.getEntityTableName(), metaEnableMachine.getEntityFieldName(), value, ids);
    }

    /**
     * 构建返回对象
     * @param success 数据库更新成功条数
     * @param fail 数据库更新失败条数
     * @return
     */
    private BulkResult createBulkResult(int success, int fail) {
        return MetaUtils.createBulkResult(new BulkMessage(200, success, "更新成功"),
                fail > 0 ? new BulkMessage(
                        BusinessCode.DatabaseUpdateError.getCode(), fail, "更新失败，数据库错误") : null);
    }
}
