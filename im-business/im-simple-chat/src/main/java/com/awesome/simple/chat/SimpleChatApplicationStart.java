package com.awesome.simple.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * projectName：imcloud
 * className ：SimpleChatApplicationStart
 * createTime：2019/12/15 9:03 PM
 * creator：awesome
 * @author awesome
 */
@SpringBootApplication
@MapperScan("com.awesome.simple.chat.mapper")
public class SimpleChatApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(SimpleChatApplicationStart.class);
    }
}
