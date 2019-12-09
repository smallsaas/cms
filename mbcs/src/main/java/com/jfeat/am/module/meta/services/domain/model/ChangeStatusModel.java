package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;

public class ChangeStatusModel implements Serializable{

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChangeStatusModel{" +
                "status='" + status + '\'' +
                '}';
    }
}
