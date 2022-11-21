package com.jfeat.module.album.services.domain.service.impl;

import com.jfeat.module.album.services.domain.service.ImageRegexService;
import com.jfeat.module.album.utils.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageRegexServiceImpl implements ImageRegexService {

    private static final String fileName = "imageExpression.properties";

    @Override
    public Map<String, String> getALlRegex() {
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

    public List<String> parseImageExpression(String expression){

        Map<String,String> regexMap = getALlRegex();

//       下标 0-标识符  1-排列顺序
        List<String> stringList = new ArrayList<>();

        for (String key : regexMap.keySet()) {
            if (expression.startsWith(regexMap.get(key))) {
                String pattern = "^".concat(regexMap.get(key)).concat("\\[(.*)\\].*");
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(expression);
                stringList.add(regexMap.get(key));
                if (m.find()){
                    stringList.add(m.group(1));
                }

                break;
            }
        }

        return stringList;

    }

}
