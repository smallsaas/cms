package com.jfeat.am.module.notification.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
public class RemindEndpoint  {

    @Resource
    UserNotifyService userNotifyService;

    @ApiOperation("生成各种未读的数量并返回")
    @PostMapping("/remind")
    public Tip pullRemind(@RequestParam(required = false, defaultValue = "0") Integer isRead) {
        Long userId = JWTKit.getUserId();
        userNotifyService.pullRemind(userId);
        List<Map<String, Object>> maps = userNotifyService.queryTypeCount(userId, isRead);
        return SuccessTip.create(maps);
    }

    @ApiOperation("生成各种未读的数量分类并返回")
    @PostMapping("/type/remind")
    public Tip pullAllTypeRemind(@RequestParam(required = false, defaultValue = "0") Integer isRead) {
        Long userId = JWTKit.getUserId();
        userNotifyService.pullRemind(userId);
        List<Map<String, Object>> maps = userNotifyService.returnTypeCount(userId, isRead);
        return SuccessTip.create(maps);
    }

    @ApiOperation("批量设为已读")
    @PostMapping("/remind/clear")
    public Tip clear(@RequestBody List<Long> ids) {
        Integer affected = 0;
        for (Long id : ids) {
            affected += userNotifyService.read(id);
        }
        return SuccessTip.create(affected);
    }
}
