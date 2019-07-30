package com.uzhizhe.ninebot;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Desc Test application
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-30
 */
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class TestApplication {

    @Before
    public void before() {
        log.info("===> Test satart <===");
        log.info("===> Test satart <===");
        log.info("===> Test satart <===");
    }

    @After
    public void after() {
        log.info("===> Test end <===");
        log.info("===> Test end <===");
        log.info("===> Test end <===");
    }
}
