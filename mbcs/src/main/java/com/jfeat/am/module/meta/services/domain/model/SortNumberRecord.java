package com.jfeat.am.module.meta.services.domain.model;

/**
 * 用于获取实体id及排序类
 */
public class SortNumberRecord {

    private Long id;

    private Integer sortNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}
