package com.jfeat.am.module.meta.services.domain.model;

import com.jfeat.crud.plus.model.IdVersion;

import java.util.List;

public class BulkChangeStatusWithVersionModel {

    private List<IdVersion> ids;

    private String status;

    public List<IdVersion> getIds() {
        return ids;
    }

    public void setIds(List<IdVersion> ids) {
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
