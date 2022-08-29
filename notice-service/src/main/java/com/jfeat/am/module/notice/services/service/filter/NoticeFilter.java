package com.jfeat.am.module.notice.services.service.filter;

import com.jfeat.am.module.notice.services.definition.NoticeStatus;
import com.jfeat.am.module.notice.services.definition.NoticeTypes;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDFilter;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vincenthuang on 06/08/2018.
 */
@Component
public class NoticeFilter implements CRUDFilter<Notice> {


    @Override
    public void filter(Notice notice, boolean insertOrUpdate) {

        if (insertOrUpdate) {
            notice.setCreateTime(new Date());
            notice.setUpdateTime(notice.getCreateTime());
            notice.setEnabled(1);
            notice.setUpdateTime(null);

            /// only Draft & Publish allow
            if (notice.getStatus() == null || notice.getStatus().length() == 0) {
                notice.setStatus(NoticeStatus.Draft.toString());
            } else {
                if (notice.getStatus().equals(NoticeStatus.Draft.toString()) ||
                        notice.getStatus().equals(NoticeStatus.Publish.toString())) {
                    // ok
                } else {
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


        } else {
            notice.setUpdateTime(new Date());

            // check status, only Draft allowed
            if (notice.getStatus().equals(NoticeStatus.Draft.toString())) {

            } else {
                throw new BusinessException(BusinessCode.ErrorStatus);
            }
        }
    }

    @Override
    public String[] ignore(boolean b) {
        return new String[0];
    }


    public Boolean filter(Notice notice) {


        Date nowDateTime = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        String nowTimeStr = simpleDateFormat.format(nowDateTime);

        Date nowTime = null;
        try {
            nowTime = simpleDateFormat.parse(nowTimeStr);
        } catch (ParseException e) {

        }
//        判读公告是否启用
        if (notice.getStatus()!=null && notice.getStatus().equals(NoticeStatus.Publish.toString())){

//            当没有周期时 按照开始时间和结束时间发布公告
            if (notice.getPeriodType() == null || notice.getPeriodType().equals(Notice.PERIOD_TYPE_NOT_SET)) {
                if ((notice.getStartTime() != null && notice.getStartTime().before(nowDateTime)) && (notice.getEndTime() != null && notice.getEndTime().after(nowDateTime))) {
                    return true;
                }

//                当周期为每天时 获取 小时 分钟 进行比较
            } else if (notice.getPeriodType().equals(Notice.PERIOD_TYPE_EVERY_DAY)) {
                String startTimeStr = simpleDateFormat.format(notice.getStartTime());
                String endTimeStr = simpleDateFormat.format(notice.getEndTime());
                try {
                    Date startTime = simpleDateFormat.parse(startTimeStr);
                    Date endTime = simpleDateFormat.parse(endTimeStr);
                    if (startTime.before(nowTime) && endTime.after(nowTime)) {
                        return true;
                    }
                } catch (ParseException e) {

                }

//                当周期为永久时 只看开始时间
            } else if (notice.getPeriodType().equals(Notice.PERIOD_TYPE_FOREVER)) {
                if (notice.getStartTime() != null && notice.getStartTime().before(nowDateTime)) {
                    return true;
                }
            }

        }


        return false;
    }


//    公告类型优先级
    public Integer getPriority(String noticeType){
        if (noticeType.equals(NoticeTypes.SYSTEM)){
            return 0;
        }
        else if (noticeType.equals(NoticeTypes.INTERNAL)){
            return 1;
        }
        else if (noticeType.equals(NoticeTypes.EXTERNAL)){
            return 2;
        }else {
            return null;
        }
    }

}
