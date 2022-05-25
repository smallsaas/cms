package com.jfeat.am.module.notice.services.service.utils;

import java.io.*;

public class ReaderFile {
    public static String readerFile(String path){
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            while((len = fis.read(buffer)) != -1){
                baos.write(buffer,0,len);

            }

            fis.close();
            baos.close();
            return baos.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
