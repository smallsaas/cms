package com.jfeat.module.rss.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static  JSONArray removeElement(JSONArray jsonArray,String... elements){
        if (jsonArray!=null&&jsonArray.size()>0&&elements.length>0){
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject!=null){
                    for (String element:elements){
                        jsonObject.remove(element);
                    }
                }
            }
        }



        return jsonArray;
    }
}
