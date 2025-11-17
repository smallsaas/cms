package com.jfeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot方式启动类
 *
 * @author Admin
 * @Date 2017/5/21 12:06
 */
@Slf4j
@SpringBootApplication
public class AmApplication{

    public static void main(String[] args) {
        SpringApplication.run(AmApplication.class, args);
        log.info("CMS app is success!");
    }
}






