package com.jfeat.module.rss.services.domain.service;

import java.util.List;
import java.util.Map;

public interface ImageRegexService {

    Map<String,String> getALlRegex();

    String getKeyByRegex(String regex);

    String getRegexByKey(String key);

    public List<String> parseImageExpression(String expression);
}
