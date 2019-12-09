package com.jfeat.am.module.notice.services.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notice.services.definition.NoticeStatus;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@Service
public class NoticeServiceImpl extends CRUDServiceOnlyImpl<Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    protected BaseMapper<Notice> getMasterMapper() {
        return noticeMapper;
    }


    @Override
    public Integer updateMaster(Notice notice) {

        // change status via update is not allowed
        Notice originOne = noticeMapper.selectById(notice.getId());
        if(originOne==null){
            throw new BusinessException(BusinessCode.BadRequest);
        }

        if(originOne.getStatus().equals(NoticeStatus.Draft.toString()) ||
                originOne.getStatus().equals(NoticeStatus.Deprecated.toString())
                ) {
            /// do not update status
            notice.setStatus(null);

            return super.updateMaster(notice);
        }

        throw new BusinessException(BusinessCode.ErrorStatus.getCode(), "状态错误：仅支持两种状态下修改[Draft,Deprecated],当前状态:"+originOne.getStatus());
    }

    @Override
    public Integer updateMaster(Notice notice, CRUDFilter<Notice> filter) {

        Notice originOne = noticeMapper.selectById(notice.getId());
        if(originOne==null){
            throw new BusinessException(BusinessCode.BadRequest);
        }

        if(originOne.getStatus().equals(NoticeStatus.Draft.toString()) ||
                originOne.getStatus().equals(NoticeStatus.Deprecated.toString())
                ) {

            /// do not update status
            notice.setStatus(originOne.getStatus());

            return super.updateMaster(notice, false);
        }

        //如果已过期
        if(originOne.getEndTime().before(new Date())){
            notice.setStatus(NoticeStatus.Deprecated.toString());
            return super.updateMaster(notice, false);
        }

        throw new BusinessException(BusinessCode.ErrorStatus.getCode(), "只允许两种状态下可修改公告 [Draft,Deprecated] 当前状态："+notice.getStatus());
    }

    @Override
    public Integer publishNotice(Long id) {
        int affect = 0;
        Notice notice = noticeMapper.selectById(id);
        notice.setUpdateTime(new Date());

        if(notice.getStatus().equals(NoticeStatus.Draft.toString())){
            // 草稿状态，可发布
            notice.setStatus(NoticeStatus.Publish.toString());
            affect += noticeMapper.updateById(notice);

        }else if(notice.getStatus().equals(NoticeStatus.Deprecated.toString())){
            /// 已下架, 重新发布
            notice.setStatus(NoticeStatus.Publish.toString());
            affect += noticeMapper.updateById(notice);

        }else {
            // 状态错误
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        return affect;
    }


    @Override
    public Integer deprecateNotice(Long id) {
        Notice notice = noticeMapper.selectById(id);
        if(notice==null){
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "无效的公告ID：" + id);
        }
        if(notice.getStatus().equals(NoticeStatus.Publish.toString())){
            notice.setStatus(NoticeStatus.Deprecated.toString());
        }else{
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        return noticeMapper.updateById(notice);
    }



    /**
     * enalbe/disable notice
     * @param id
     * @return
     */

    @Override
    public Integer enableNotice(Long id, Integer enable) {

        Notice notice = noticeMapper.selectById(id);
        if(notice==null){
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "无效的公告ID：" + id);
        }
        if(notice.getEnabled() == enable){
            return 0;
        }

        // Draft Status not allow
        if(notice.getStatus().equals(NoticeStatus.Draft.toString())){
            throw new BusinessException(BusinessCode.ErrorStatus.getCode(), "草稿状态下不能进行 停用/启用操作");
        }

        /// 下架/重新上架
        notice.setUpdateTime(new Date());
        notice.setEnabled(enable);

        return super.updateMaster(notice,false);
    }


    @Override
    public Integer switchEnabled(Long id) {
        Notice notice = noticeMapper.selectById(id);
        notice.setUpdateTime(new Date());
        if (notice.getEnabled() == 1) {
            notice.setEnabled(0);
        } else {
            notice.setEnabled(1);
        }
        return noticeMapper.updateById(notice);
    }
}


