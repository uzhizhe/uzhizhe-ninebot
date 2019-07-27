package com.uzhizhe.ninebot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description WebApplication
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-24
 **/
@SpringBootApplication
@Slf4j
public class WebApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);
        log.info("WebApplication startup successful ...");
        log.info("WebApplication startup successful ...");
        log.info("WebApplication startup successful ...");
    }
}
