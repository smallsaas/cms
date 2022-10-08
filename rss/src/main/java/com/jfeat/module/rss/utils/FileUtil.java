package com.jfeat.module.rss.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileUtil {


//    读取全部文件

    public static Map<String, String> readProperties(File file) {
        Map<String, String> map = new HashMap<String, String>();
        InputStream in = null;
        Properties p = new Properties();
        ;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            p.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<Map.Entry<Object, Object>> entrySet = p.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }


}
