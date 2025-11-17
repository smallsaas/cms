package com.jfeat.am.module.advertisement.services.domain.model.record;

import com.jfeat.am.module.advertisement.services.persistence.model.Ad;


public class AdRecord extends Ad {

    /**
     * 广告分组名称
     */
    String groupName;

    /**
     * TODO, 广告投放策略, 分析作用
     */
    String[] strategyArray;


    public String[] getStrategyArray() {
        return strategyArray;
    }

    public void setStrategyArray(String[] strategyArray) {
        this.strategyArray = strategyArray;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
