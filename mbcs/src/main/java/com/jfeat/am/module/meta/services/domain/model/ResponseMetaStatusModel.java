package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;

/**
 * 用于获取状态机的返回对象
 */
public class ResponseMetaStatusModel implements Serializable {

    private Long id;

    private String entity;

    private String fromStatus;

    private String toStatus;

    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "ResponseMetaStatusModel{" +
                "id=" + id +
                ", entity='" + entity + '\'' +
                ", fromStatus='" + fromStatus + '\'' +
                ", toStatus='" + toStatus + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
