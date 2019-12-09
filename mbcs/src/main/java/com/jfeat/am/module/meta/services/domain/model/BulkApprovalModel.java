package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * 批量审核操作接收类
 */
public class BulkApprovalModel implements Serializable {

    /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 附加条件
     */
    private AdditionModel addition;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public AdditionModel getAddition() {
        return addition;
    }

    public void setAddition(AdditionModel addition) {
        this.addition = addition;
    }

    @Override
    public String toString() {
        return "BulkApprovalModel{" +
                "ids=" + ids +
                ", addition=" + addition +
                '}';
    }
}
