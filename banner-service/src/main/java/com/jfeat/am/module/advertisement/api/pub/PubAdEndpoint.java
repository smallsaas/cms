package com.jfeat.am.module.advertisement.api.pub;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

/**
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/api/pub/cms/ad")
@Api("轮播图-Banner")
public class PubAdEndpoint {

    @Resource
    AdService adService;

    // @Resource
    // AdGroupMapper adGroupMapper;

    // @Resource
    // AdGroupService adGroupService;

     @Resource
     QueryAdDao queryAdDao;

    // @Resource
    // QueryAdLibraryDao queryAdLibraryDao;

    /**
     * 检查字符串是否为base64编码，如果是则解码，否则直接使用
     */
    private String decodeIfBase64(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        try {
            // 检查是否是有效的base64编码
            // 尝试解码，如果成功且结果合理，则认为是base64编码
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

            // 简单验证解码结果是否合理（非空且包含可打印字符）
            if (!decoded.isEmpty() && decoded.matches("[\\x20-\\x7E\\u4e00-\\u9fa5]+")) {
                return decoded;
            }
        } catch (IllegalArgumentException e) {
            // 不是有效的base64编码，直接使用原值
        }

        return input;
    }

    @GetMapping("/group/{group_name}")
    @ApiOperation("根据分组标识获取轮播图")
    public Tip getAdByGroupId(@PathVariable String group_name) {
        // 检查group_name是否为base64编码，如果是则解码，否则直接使用
        String decodedGroupName = decodeIfBase64(group_name);

        return SuccessTip.create(adService.getAdRecordsByGroup(decodedGroupName));
    }

    // @ApiOperation("按组获取轮播图分组")
    // @GetMapping("/allGroup")
    // public Tip getAdsFromGroup(
    //         @RequestParam(name = "search", required = false) String search,
    //         @RequestParam(name = "groupId", required = true) Integer groupId) {
    //     /*
    //     验证用户是否是运营身份
    //      */
    //     if (JWTKit.getUserId() == null) {
    //         throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
    //     }

    //     /*  if (!authentication.verifyOperation(JWTKit.getUserId())) {
    //         throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
    //     }
    //     */
    //     if(search!=null && search.trim().length()>0){
    //         return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)
    //                 .like("name",search)
    //                 .or()
    //                 .like("type",search)
    //                 .eq("group_id",groupId)
    //         ));
    //     }else  return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)));
    // }

}
