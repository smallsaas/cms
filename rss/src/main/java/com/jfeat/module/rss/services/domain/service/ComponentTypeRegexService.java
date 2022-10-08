package com.jfeat.module.rss.services.domain.service;

import java.util.Map;

public interface ComponentTypeRegexService {

    Map<String,String> getALlRegex();

    String getKeyByRegex(String regex);

    String getRegexByKey(String key);
}
