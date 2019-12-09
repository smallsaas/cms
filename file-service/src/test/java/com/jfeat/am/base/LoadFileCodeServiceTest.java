package com.jfeat.am.base;

import com.jfeat.module.fs.service.LoadFileCodeService;
import com.jfeat.module.fs.service.impl.LoadFileCodeServiceImpl;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jackyhuang on 2018/1/3.
 */
public class LoadFileCodeServiceTest {

    LoadFileCodeService loadFileCodeService = new LoadFileCodeServiceImpl();

    @Test
    public void testCache() throws InterruptedException {
        String code = loadFileCodeService.genAndGetCode("abc");
        System.out.println(code);
        System.out.println(loadFileCodeService.checkCode(code));
        TimeUnit.SECONDS.sleep(6);
        System.out.println(loadFileCodeService.checkCode(code));
    }
}
