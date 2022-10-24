package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.Queue;

public interface RssIntermediateControl {

    void involve(RssItem rssItem, Queue<String> queue);
}
