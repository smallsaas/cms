package com.jfeat.module.rss.services.domain.service.impl;

import com.jfeat.module.rss.services.domain.service.ComponentTypeRegexService;
import com.jfeat.module.rss.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class ComponentTypeRegexServiceImp implements ComponentTypeRegexService {

    private static final String fileName = "componentTypeRegex.properties";

    @Override
    public Map<String, String> getALlRegex() {
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();
        return FileUtil.readProperties(new File(path));
    }

    @Override
    public String getKeyByRegex(String regex) {
        Map<String,String> map = getALlRegex();
        for (String key: map.keySet()){
            if (map.get(key).equals(regex)){
                return key;
            }
        }
        return null;
    }

    @Override
    public String getRegexByKey(String key) {
        Map<String,String> map = getALlRegex();
        if (map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }
}
