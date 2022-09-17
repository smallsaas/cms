package com.jfeat.am.module.notice.api.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notice.services.definition.NoticeStatus;
import com.jfeat.am.module.notice.services.definition.NoticeTypes;
import com.jfeat.am.module.notice.services.domain.dao.QueryNoticeDao;
import com.jfeat.am.module.notice.services.domain.model.EnabledStatus;
import com.jfeat.am.module.notice.services.domain.model.NoticeRequest;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import com.jfeat.am.module.notice.services.service.filter.NoticeFilter;
import com.jfeat.am.module.notice.services.service.utils.ReaderFile;
import com.jfeat.am.module.notice.task.AuditNoticeJob;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.util.DateTimeKit;
import com.jfeat.crud.plus.META;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api("公告")
@RequestMapping("/api/u/cms/notice/notices")
public class UserNoticeEndpoint {

    @Resource
    NoticeService noticeService;

    @Resource
    QueryNoticeDao queryNoticeDao;

    @Resource
    AuditNoticeJob auditNoticeJob;

    @Resource
    NoticeMapper noticeMapper;

    @Resource
    NoticeFilter noticeFilter;

    @GetMapping("/{id}")
    @ApiOperation(value = "查看公告", response = Notice.class)
    public Tip getNotice(@PathVariable Long id) {
        Notice notice = queryNoticeDao.queryNoticesById(id);

//        当content为空时读取content_path内容
        if (("".equals(notice.getContent()) || notice.getContent()==null) && (notice.getContentPath()!=null || "".equals(notice.getContentPath()))){
            String path = notice.getContentPath();
            notice.setContent(ReaderFile.readerFile(path));
        }
        notice.setViewNumber(notice.getViewNumber()+1);
        noticeMapper.updateById(notice);
//        noticeService.retrieveMaster(id)
        return SuccessTip.create(notice);
    }


    @GetMapping
    @ApiOperation(value = "公告列表", response = NoticeRequest.class)
    public Tip queryNotices(
            Page<NoticeRequest> page,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "100") Integer pageSize,
            @RequestParam(name = "type", required = false) String[] type,
            @RequestParam(name = "status", required = false) String status,
            @Range(min = 0, max = 1) @RequestParam(name = "enabled", required = false) Integer enabled,
            @Range(min = 0, max = 1) @RequestParam(name = "expired", required = false) Integer expired,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "content", required = false) String content) {

        Long userId = JWTKit.getUserId();
        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }

        page.setCurrent(pageNum);

        page.setSize(pageSize);

        // type=External,Internal,System
        if(type!=null && type.length<=2){
            for(String t : type){
                if(t!=null){
                    if(t.compareTo(NoticeTypes.EXTERNAL.toString())==0 ||
                            t.compareTo(NoticeTypes.INTERNAL.toString())==0 ||
                            t.compareTo(NoticeTypes.SYSTEM.toString())==0){
                        //ok
                    }else{
                        throw new BusinessException(BusinessCode.BadRequest.getCode(), "类型错误：Only [EXTERNAL,Internal,System] " + t);
                    }
                }
            }
        }

        // 检查过期, 类型过滤不作检查
        if(type==null || type.length==0) {
            auditNoticeJob.auditNotice();
        }

        NoticeRequest notice = new NoticeRequest();
        //notice.setType(type);
        notice.setStatus(status);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setEnabled(enabled);
        notice.setNoticeType(Notice.NOTICE_Type_NOTICE);

        if (META.enabledSaas()) {
            notice.setOrgId(JWTKit.getOrgId());
        }
        List<NoticeRequest> noticeRequestList = queryNoticeDao.findNotices(page, notice, expired, type,search);

        //        当content为空时读取content_path内容
//        for (Notice notice1:noticeRequestList){
//            if ((notice1.getContent().equals("") || notice1.getContent()==null) && (notice1.getContentPath()!=null || notice.getContentPath().equals(""))){
//                String path = notice1.getContentPath();
//                notice1.setContent(ReaderFile.readerFile(path));
//            }
//        }
        List<NoticeRequest> newNoticeRequestList=new ArrayList<>();
        Date nowDate=new Date();
        //此处处理距离结束日期的时间 传给前端
        for (NoticeRequest noticeRequest:noticeRequestList)
        {
            if(noticeRequest.getEndTime()!=null){
                if(noticeRequest.getEndTime().before(nowDate)){
                    noticeRequest.setIsEnd("end");
                    //设为已过期状态
                    noticeRequest.setEnabled(EnabledStatus.EnableEnd);
//                    noticeRequest.setStatus(NoticeStatus.Expired.toString());
                }else
                {
                    noticeRequest.setIsEnd("notEnd");

                }


                Long endTime=noticeRequest.getEndTime().getTime()-nowDate.getTime();
                //如果已过期
                if(endTime<0){
                    noticeRequest.setEndDate(0L);
                }
                //未过期
                else
                {
                    noticeRequest.setEndDate(endTime/86400000+1);
                }
                //24*60*60*1000
            }

            newNoticeRequestList.add(noticeRequest);
        }


        List<NoticeRequest> resultNotices = new ArrayList<>();

//        过滤符合条件的
        for (NoticeRequest noticeRequest:newNoticeRequestList){
            if (noticeFilter.filter(noticeRequest)){
                resultNotices.add(noticeRequest);
            }
        }

        for (NoticeRequest noticeRequest:resultNotices){
            noticeRequest.setCreateTimeStr(DateTimeKit.toTimeline(noticeRequest.getCreateTime()));
        }


        page.setSize(resultNotices.size());
        page.setRecords(resultNotices);

        return SuccessTip.create(page);
    }


    /**
     * 最新公告（必须是未过期，且已启用的。结果按有效时间倒序）
     */
    @GetMapping("/recent/notices")
    @ApiOperation("最新公告（必须是未过期，且已启用的。结果按有效时间倒序）")
    public Tip latestNotices(Page<Notice> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                             @RequestParam(name = "type", required = false) String type) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        if(type!=null && type.length()>0){
            if(type.compareTo(NoticeTypes.EXTERNAL.toString())==0 || type.compareTo(NoticeTypes.INTERNAL.toString())==0){
                //ok
            }else{
                throw new BusinessException(BusinessCode.BadRequest.getCode(), "类型错误：Only allow [External,Internal], but " + type);
            }
        }

        page.setRecords(queryNoticeDao.findRecentNotices(page, type));

        return SuccessTip.create(page);
    }






}
