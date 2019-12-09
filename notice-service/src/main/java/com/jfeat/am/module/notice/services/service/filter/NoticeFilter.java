package com.jfeat.am.module.notice.services.service.filter;

import com.jfeat.am.module.notice.services.definition.NoticeStatus;
import com.jfeat.am.module.notice.services.definition.NoticeTypes;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDFilter;

import java.util.Date;

/**
 * Created by vincenthuang on 06/08/2018.
 */
public class NoticeFilter implements CRUDFilter<Notice> {

    @Override
    public void filter(Notice notice, boolean insertOrUpdate) {

        if(insertOrUpdate) {
            notice.setCreateTime(new Date());
            notice.setUpdateTime(notice.getCreateTime());
            notice.setEnabled(1);
            notice.setUpdateTime(null);

            /// only Draft & Publish allow
            if(notice.getStatus()==null || notice.getStatus().length()==0){
                notice.setStatus(NoticeStatus.Draft.toString());
            }else{
                if(notice.getStatus().equals(NoticeStatus.Draft.toString()) ||
                        notice.getStatus().equals(NoticeStatus.Publish.toString())){
                    // ok
                }else{
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "Invalid init notice status: " + notice.getStatus());
                }
            }

            /// check type
            if (notice.getType() == null) {
                notice.setType(NoticeTypes.SYSTEM);
            }

            if (notice.getType().equals(NoticeTypes.SYSTEM.toString())
                    || notice.getType().equals(NoticeTypes.INTERNAL.toString())
                    || notice.getType().equals(NoticeTypes.EXTERNAL.toString())) {
                // ok
            } else {
                throw new BusinessException(BusinessCode.NotSupport.getCode(), "Dot not support " + notice.getType() + ", should be one of [System|Internal|EXTERNAL]");
            }


        }else {
            notice.setUpdateTime(new Date());

            // check status, only Draft allowed
            if(notice.getStatus().equals(NoticeStatus.Draft.toString())){

            }else{
                throw new BusinessException(BusinessCode.ErrorStatus);
            }
        }
    }

    @Override
    public String[] ignore(boolean b) {
        return new String[0];
    }
}
