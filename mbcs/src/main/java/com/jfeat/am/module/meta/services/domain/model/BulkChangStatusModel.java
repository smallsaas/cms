package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;
import java.util.List;

public class BulkChangStatusModel implements Serializable {

    private List<Long> ids;

    private String status;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BulkChangStatusModel{" +
                "ids=" + ids +
                ", status='" + status + '\'' +
                '}';
    }
}
