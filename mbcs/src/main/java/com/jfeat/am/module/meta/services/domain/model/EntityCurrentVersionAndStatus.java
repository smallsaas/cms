package com.jfeat.am.module.meta.services.domain.model;

public class EntityCurrentVersionAndStatus {

    private Long id;

    private Integer version;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EntityCurrentVersionAndStatus{" +
                "id=" + id +
                ", version=" + version +
                ", status='" + status + '\'' +
                '}';
    }
}
