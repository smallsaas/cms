package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;

/**
 * 审批内容接收类
 */
public class AdditionModel implements Serializable {

    /**
     * 备注、建议
     */
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "AdditionModel{" +
                "note='" + note + '\'' +
                '}';
    }
}
