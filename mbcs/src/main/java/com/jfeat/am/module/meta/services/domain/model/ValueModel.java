package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;

public class ValueModel implements Serializable {

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueModel{" +
                "value='" + value + '\'' +
                '}';
    }
}
