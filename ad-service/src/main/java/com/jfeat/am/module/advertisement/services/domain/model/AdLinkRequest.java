package com.jfeat.am.module.advertisement.services.domain.model;

import com.jfeat.am.module.advertisement.services.persistence.model.AdLinkDefinition;

import java.util.List;

public class AdLinkRequest extends AdLinkDefinition {

    List<AdLinkDefinition> children;

    public List<AdLinkDefinition> getChildren() {
        return children;
    }

    public void setChildren(List<AdLinkDefinition> children) {
        this.children = children;
    }
}
