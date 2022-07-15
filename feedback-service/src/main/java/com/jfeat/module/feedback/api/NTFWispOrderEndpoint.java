package com.jfeat.module.feedback.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.domain.dao.QueryWispOrderDao;
import com.jfeat.module.feedback.services.gen.persistence.model.WispOrderRecordDetail;
import com.jfeat.module.feedback.services.service.NFTWispOrderService;
import com.jfeat.module.feedback.services.service.impl.NFTWispOrderServiceImpl;
import com.jfeat.module.smallsaas.ticket.api.ComplainReplyRecordEndpoint;
import com.jfeat.module.smallsaas.ticket.api.request.ComplainReplyGenerateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@Api("申诉记录")
@RequestMapping("/api/crud/oms/wispOrder/wispOrders")
public class NTFWispOrderEndpoint {

    @Resource
    NFTWispOrderServiceImpl nftWispOrderService;

    @Resource
    private ComplainReplyRecordEndpoint replyRecordEndpoint;

    @Resource
    QueryWispOrderDao queryWispOrderDao;



    @ApiOperation(value = "获取申诉记录", response = WispOrderRecordDetail.class)
    @GetMapping("/wisp/complains")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "status", dataType = "String"),
            @ApiImplicitParam(name = "title", dataType = "String"),
            @ApiImplicitParam(name = "phone", dataType = "String")
    })
    public Tip getComplainList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "requestType", required = false, defaultValue = "") String requestType,
                               @RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "phone", required = false) String phone,
                               @RequestParam(name = "status", required = false )String status) {
        return SuccessTip.create(nftWispOrderService.queryComplainList(pageNum, pageSize, requestType , status , title ,phone));
    }

    @ApiOperation(value = "返回投诉类型")
    @GetMapping("/wisp/complain/type/{type}")
    public Tip queryComplainType(@PathVariable(name = "type") String type) {
        return SuccessTip.create(queryWispOrderDao.queryComplainType(type));
    }


    @ApiOperation(value = "管理员进行申诉回复")
    @PostMapping("/wisp/complain/reply")
    public Tip complainByOrderId(@RequestBody ComplainReplyGenerateRequest request) {
        return replyRecordEndpoint.createComplainReply(request.setReplierId(JWTKit.getUserId()).setIsManagerReply(1));
    }

    @ApiOperation(value = "管理员查看申诉单详情")
    @GetMapping("/wisp/complain/{id}")
    public Tip complainByOrderId(@PathVariable(name = "id") Long id) {
        return SuccessTip.create(nftWispOrderService.managerComplainDetailRecord(id));
    }


}

