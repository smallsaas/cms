package com.jfeat.am.module.notification.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.constant.Const;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.am.module.notification.services.domain.model.NotifyModel;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@RestController
@RequestMapping("/api/notification")
public class NotifyEndpoint  {


    @Resource
    private NotifyService notifyService;
    @Resource
    private UserNotifyService userNotifyService;

    @ApiOperation("返回当前用户的通知列表")
    @GetMapping("/notify")
    public Tip queryNotify(Page<NotifyModel> page,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "targetTypes", required = false) String[] targetTypes,
                           @RequestParam(required = false) Integer isRead) {
        Long userId = JWTKit.getUserId();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<String> typeList = targetTypes == null ? new ArrayList<>() : Arrays.asList(targetTypes);
        List<NotifyModel> notifies = notifyService.paginationNotifies(page, userId, typeList, isRead);
        if (!notifies.isEmpty()) {
            for (NotifyModel notify : notifies) {
                Long id = notify.getUserNotifyId();
                if (id != null && Const.UN_READ == notify.getIsRead()) {
                    userNotifyService.read(id);
                }
            }
        }
        page.setRecords(notifies);
        return SuccessTip.create(page);
    }

    @ApiOperation("返回当前用户的通知列表 -> sort by actions")
    @GetMapping("/app/notify")
    public Tip searchNotifies(Page<NotifyModel> page,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "actions", required = false) String[] actions) {
        Long userId = JWTKit.getUserId();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<String> typeList = actions == null ? new ArrayList<>() : Arrays.asList(actions);
        List<NotifyModel> notifies = notifyService.searchNotifies(page, userId, typeList);
        if (!notifies.isEmpty()) {
            for (NotifyModel notify : notifies) {
                Long id = notify.getUserNotifyId();
                if (id != null && Const.UN_READ == notify.getIsRead()) {
                    userNotifyService.read(id);
                }
            }
        }
        page.setRecords(notifies);
        return SuccessTip.create(page);
    }
}
