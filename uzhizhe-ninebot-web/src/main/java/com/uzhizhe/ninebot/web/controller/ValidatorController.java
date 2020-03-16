package com.uzhizhe.ninebot.web.controller;

import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.web.vo.ValidatorBeanA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc jsr303 validator
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-11
 */
@RestController
@Slf4j
public class ValidatorController {

    @PostMapping(value = "/uzhizhe/ninebot/validator")
    @CrossOrigin("*")
    public ResponseDto validator1(@RequestBody @Validated ValidatorBeanA validatorBeanA) {
        log.info("BeanA:{}", validatorBeanA);

        return ResponseDto.success();
    }

}
