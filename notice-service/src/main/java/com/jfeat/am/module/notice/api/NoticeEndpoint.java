package com.jfeat.am.module.notice.api;

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
import com.jfeat.am.module.notice.services.service.utils.RegexScript;
import com.jfeat.am.module.notice.task.AuditNoticeJob;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */

@RestController
@Api("公告")
@RequestMapping("/api/cms/notice/notices")
public class NoticeEndpoint {

    @Resource
    NoticeService noticeService;

    @Resource
    QueryNoticeDao queryNoticeDao;

    @Resource
    AuditNoticeJob auditNoticeJob;

    @Resource
    NoticeMapper noticeMapper;




    /**
     * CRUD
     *
     * @param entity
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加公告", response = Notice.class)
    public Tip createNotice(@RequestBody Notice entity,@RequestParam(value = "template",required = false,defaultValue = "false") Boolean template) {
        entity.setTemplate(template);
        entity.setAuthor(JWTKit.getAccount());


        if (!template&&entity.getTemplateId()!=null){
            Notice notice =  noticeMapper.selectById(entity.getTemplateId());
            if (notice==null){
                throw new BusinessException(BusinessCode.BadRequest,"未找到该模板");
            }
        }

        if (entity.getOrgId()==null){
            entity.setOrgId(JWTKit.getOrgId());
        }

        if (entity.getContent()!=null){
            entity.setPictureUrl(RegexScript.getImageUrl(entity.getContent()));
        }
//        if (entity.getPictureUrl()==null || entity.getPictureUrl().equals("")){
//            throw new BusinessException(BusinessCode.BadRequest,"封面图为必传值");
//        }
        return SuccessTip.create(noticeService.createMaster(entity, new NoticeFilter()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看公告", response = Notice.class)
    public Tip getNotice(@PathVariable Long id) {
        Notice notice = queryNoticeDao.queryNoticesById(id);

//        当content为空时读取content_path内容
//        if (("".equals(notice.getContent()) || notice.getContent()==null) && (notice.getContentPath()!=null || "".equals(notice.getContentPath()))){
//            String path = notice.getContentPath();
//            notice.setContent(ReaderFile.readerFile(path));
//        }
//        noticeService.retrieveMaster(id)
        return SuccessTip.create(notice);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改公告", response = Notice.class)
    public Tip updateNotice(@PathVariable Long id, @RequestBody Notice entity) {

        if (entity.getContent()!=null){
            entity.setPictureUrl(RegexScript.getImageUrl(entity.getContent()));
        }

        if (entity.getTemplateId()!=null){
            Notice notice =  noticeMapper.selectById(entity.getTemplateId());
            if (notice==null){
                throw new BusinessException(BusinessCode.BadRequest,"未找到该模板");
            }
        }
        entity.setId(id);
        return SuccessTip.create(noticeMapper.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除公告")
    public Tip deleteNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation(value = "公告列表", response = NoticeRequest.class)
    public Tip queryNotices(
            Page<NoticeRequest> page,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "type", required = false) String[] type,
            @RequestParam(name = "status", required = false) String status,
            @Range(min = 0, max = 1) @RequestParam(name = "enabled", required = false) Integer enabled,
            @Range(min = 0, max = 1) @RequestParam(name = "expired", required = false) Integer expired,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "template",required = false) Boolean template
    ) {
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
//        notice.setOrgId(JWTKit.getOrgId());
        notice.setTemplate(template);
        List<NoticeRequest> noticeRequestList = queryNoticeDao.findNotices(page, notice, expired, type,search);

        //        当content为空时读取content_path内容
        for (Notice notice1:noticeRequestList){
            if ((notice1.getContent().equals("") || notice1.getContent()==null) && (notice1.getContentPath()!=null || notice.getContentPath().equals(""))){
                String path = notice1.getContentPath();
                notice1.setContent(ReaderFile.readerFile(path));
            }
        }
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
                    noticeRequest.setStatus(NoticeStatus.Expired.toString());
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


        page.setRecords(noticeRequestList);
        System.out.println(page);
        return SuccessTip.create(page);
    }


    /**
     * Advance API
     */

    @PutMapping("/{id}/publish")
    @ApiOperation("发布公告")
    public Tip publishNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.publishNotice(id));
    }

    @PutMapping("/{id}/deprecate")
    @ApiOperation("下架公告")
    public Tip downNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.deprecateNotice(id));
    }


    /**
     * Leagcy API
     */

    @ApiOperation("启用公告")
    @PutMapping("/{id}/enable")
    public Tip enableNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.enableNotice(id, 1));
    }

    @PutMapping("/{id}/disable")
    @ApiOperation("停止公告")
    public Tip disableNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.enableNotice(id, 0));
    }

    @PutMapping("/{id}/switchEnabled")
    @ApiOperation("启用(或取消启用)公告")
    public Tip switchEnabled(@PathVariable Long id) {
        return SuccessTip.create(noticeService.switchEnabled(id));
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

    /**
     * 以命名查询 公告
     * @param name
     * @return
     */

    @GetMapping("/getNoticesByName")
    public Tip getNoticesByName(@RequestParam("name") String name){
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        noticeQueryWrapper.eq(Notice.NAME,name);
        return SuccessTip.create(noticeMapper.selectOne(noticeQueryWrapper));
    }

    @PutMapping("/setNoticeStick/{id}")
    public Tip setNoticeStick(@PathVariable("id") Long id){
        Notice notice = queryNoticeDao.selectById(id);
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Notice.SORT_NUM).last("limit 1");
        Notice maxNotice =  noticeMapper.selectOne(queryWrapper);

        if (notice!=null){
            notice.setStick(Notice.STICK_TOP);
            if (maxNotice!=null && notice.getSortNum()!=null){
                notice.setSortNum(maxNotice.getSortNum()+1);
            }else {
                notice.setSortNum(1);
            }
            notice.setStick(Notice.STICK_TOP);
            return SuccessTip.create(noticeMapper.updateById(notice));
        }
        return SuccessTip.create();
    }

    @PutMapping("/cancelNoticeStick/{id}")
    public Tip cancelNoticeStick(@PathVariable("id") Long id){
        Notice notice = queryNoticeDao.selectById(id);
        if (notice!=null){
            notice.setStick(Notice.STICK_TOP);
            notice.setSortNum(1);
            notice.setStick(Notice.STICK_NOT_TOP);
            return SuccessTip.create(noticeMapper.updateById(notice));
        }
        return SuccessTip.create();
    }
}
