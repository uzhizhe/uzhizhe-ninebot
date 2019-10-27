package com.uzhizhe.ninebot.web.controller;

import com.monker.common.result.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-10-19
 */
@Slf4j
@RestController
public class TestController {

    @PostMapping(value = "/ninebot/test/requestBody/{}")
    @CrossOrigin("*")
    public Object testRequestBoty(String robotId) {
        log.info("TestRequestBoty:{}", robotId);
        return ResponseDto.success();
    }
}
