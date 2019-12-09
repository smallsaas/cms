package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * 批量更新实体有效位
 * 用于web接收
 * create on 2019-01-05 17:49:45
 */
public class BulkMetaEnable implements Serializable {

    private List<Long> ids;

    private Integer value;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BulkMetaEnable{" +
                "ids=" + ids +
                ", value=" + value +
                '}';
    }
}
