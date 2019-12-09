package com.jfeat.am.base;

import com.jfeat.module.fs.util.CacheMap;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by jackyhuang on 2018/1/20.
 */
public class CacheMapTest {

    //@Test
    public void test() throws InterruptedException {
        CacheMap cacheMap = CacheMap.getDefault();
        cacheMap.put("abc", "ABC");
        assertEquals("ABC", cacheMap.get("abc"));

        TimeUnit.SECONDS.sleep(61);
        assertNull(cacheMap.get("abc"));

    }
}
