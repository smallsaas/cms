package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.domain.dao.QueryComplainRecordDao;
import com.jfeat.module.feedback.services.domain.model.ComplainRecordRecord;
import com.jfeat.module.feedback.services.domain.service.ComplainRecordService;
import com.jfeat.module.feedback.services.gen.persistence.dao.CMSFeedbackMapper;
import com.jfeat.module.feedback.services.gen.persistence.dao.ComplainRecordMapper;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;
import com.jfeat.module.feedback.services.gen.persistence.model.FBComplainRequestType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Queue;

@RestController
@Api("反馈模块")
@RequestMapping("/api/u/user")
public class CloudFeedBackEndpoint {

    @Resource
    ComplainRecordService complainRecordService;

    @Resource
    ComplainRecordMapper complainRecordMapper;

    @Resource
    QueryComplainRecordDao queryComplainRecordDao;

    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/feedback/{appid}/{type}")
    public Tip feedback(@RequestBody ComplainRecord request, @PathVariable String type, @PathVariable("appid") String appid) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setAppid(appid);
        request.setComplainantId(JWTKit.getUserId());
        request.setRequestType(type);
        complainRecordMapper.insert(request);
        return SuccessTip.create();
    }

/*    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/feedback/{appid}")
    public Tip feedbackType(@RequestBody ComplainRecord request) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setComplainantId(JWTKit.getUserId());
        request.setRequestType(FBComplainRequestType.FEEDBACK.name());
        int insert = complainRecordMapper.insert(request);
        return SuccessTip.create(insert);
    }*/


    @ApiOperation(value = "反馈列表")
    @GetMapping("/feedback/{appid}")
    public Tip feedbackPage(@PathVariable("appid")String appid,
                            Page<ComplainRecordRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search,
                            @RequestParam(name = "id", required = false) Long id,

                            @RequestParam(name = "complainantId", required = false) Long complainantId,

                            @RequestParam(name = "relationOrderId", required = false) Long relationOrderId,

                            @RequestParam(name = "complainantRole", required = false) String complainantRole,

                            @RequestParam(name = "title", required = false) String title,

                            @RequestParam(name = "content", required = false) String content,

                            @RequestParam(name = "credentialLink", required = false) String credentialLink,

                            @RequestParam(name = "status", required = false) String status,

                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam(name = "createTime", required = false) Date createTime,

                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam(name = "updateTime", required = false) Date updateTime,

                            @RequestParam(name = "requestType", required = false) String requestType,
                            @RequestParam(name = "orderBy", required = false) String orderBy) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        ComplainRecordRecord record = new ComplainRecordRecord();
        record.setId(id);
        record.setComplainantId(complainantId);
        record.setRelationOrderId(relationOrderId);
        record.setComplainantRole(complainantRole);
        record.setTitle(title);
        record.setContent(content);
        record.setCredentialLink(credentialLink);
        record.setStatus(status);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);
        record.setRequestType(requestType);
        record.setAppid(appid);
        List<ComplainRecordRecord> complainRecordPage = queryComplainRecordDao.findComplainRecordPage(page, record, search, orderBy, null, null);
        page.setRecords(complainRecordPage);

        return SuccessTip.create(page);
    }

}
