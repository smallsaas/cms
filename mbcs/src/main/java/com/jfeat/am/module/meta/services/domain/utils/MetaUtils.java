package com.jfeat.am.module.meta.services.domain.utils;

import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 */
public class MetaUtils {

    /**
     * 功能描述：创建批量返回对象
     * @param success 成功
     * @param failures 失败数组
     * @return
     */
    public static BulkResult createBulkResult(BulkMessage success, BulkMessage... failures) {
        // 批量返回对象
        BulkResult bulkResult = new BulkResult();
        // 成功
        bulkResult.setSuccess(success);
        // 失败列表
        List<BulkMessage> failureList = new ArrayList<>();
        for (BulkMessage failure : failures) {
            if (null != failure) {
                failureList.add(failure);
            }
        }
        if (!CollectionUtils.isEmpty(failureList)) {
            bulkResult.setFailure(failureList);
        }
        return bulkResult;
    }
}
