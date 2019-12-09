package com.jfeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringBoot方式启动类
 *
 * @author Admin
 * @Date 2017/5/21 12:06
 */
@EnableCaching
@SpringBootApplication
public class AmApplication {

    protected final static Logger logger = LoggerFactory.getLogger(AmApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AmApplication.class, args);
    }
}
