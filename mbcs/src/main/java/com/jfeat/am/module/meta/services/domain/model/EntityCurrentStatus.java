package com.jfeat.am.module.meta.services.domain.model;

public class EntityCurrentStatus {

    private Long id;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EntityCurrentStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
