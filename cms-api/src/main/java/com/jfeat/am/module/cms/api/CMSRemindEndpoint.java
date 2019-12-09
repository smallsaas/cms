package com.jfeat.am.module.cms.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
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
@Api("CMS-消息处理及消息数量")
@RequestMapping("/api/cms/notification/remind")
public class CMSRemindEndpoint {

    @Resource
    UserNotifyService userNotifyService;

    @ApiOperation("生成各种未读的数量并返回")
    @PostMapping
    public Tip pullRemind(@RequestParam(required = false, defaultValue = "0") Integer isRead) {
        Long userId = JWTKit.getUserId();
        userNotifyService.pullRemind(userId);
        List<Map<String, Object>> maps = userNotifyService.queryTypeCount(userId, isRead);
        return SuccessTip.create(maps);
    }

    @ApiOperation("批量设为已读")
    @PostMapping("/clear")
    public Tip clear(@RequestBody List<Long> ids) {
        Integer affected = 0;
        for (Long id : ids) {
            affected += userNotifyService.read(id);
        }
        return SuccessTip.create(affected);
    }
}
