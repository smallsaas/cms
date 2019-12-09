package com.jfeat.am.module.advertisement.services.domain.model.record;

import com.jfeat.am.module.advertisement.services.domain.model.AdImage;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;

import java.util.List;

public class AdRecord extends Ad {
    String groupName;

    List<AdImage> images;

   String[] strategyArray;

    public List<AdImage> getImages() {
        return images;
    }

    public void setImages(List<AdImage> images) {
        this.images = images;
    }

    public String[] getStrategyArray() {
        return strategyArray;
    }

    public void setStrategyArray(String[] strategyArray) {
        this.strategyArray = strategyArray;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
