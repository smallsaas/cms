package com.jfeat.module.rss.services.domain.service.impl;

import com.jfeat.module.rss.services.domain.service.ComponentTypeRegexService;
import com.jfeat.module.rss.utils.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class ComponentTypeRegexServiceImp implements ComponentTypeRegexService {

    private static final String fileName = "componentTypeRegex.properties";

    @Override
    public Map<String, String> getALlRegex() {
//        String path  = this.getClass().getP
//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()+File.separator+fileName;
//        String path = ClassLoader.getSystemClassLoader().getResource(fileName).getPath();
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        try {
            InputStream inputStream = classPathResource.getInputStream();
            return FileUtil.readProperties(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

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
