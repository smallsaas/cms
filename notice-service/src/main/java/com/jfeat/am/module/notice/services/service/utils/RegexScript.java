package com.jfeat.am.module.notice.services.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexScript {

    public static String getImageUrl(String content){
        Pattern p = Pattern.compile("src=\"(.*?)\"");
        Matcher m = p.matcher(content);
        List<String> cover = new ArrayList<>();
        while(m.find()){ //寻找下一个匹配的子文本
            String s = "\"".concat(m.group(1)).concat("\""); //提取
            System.out.println(s); //输出
            cover.add(s);
        }
        return cover.toString();
    }
}
