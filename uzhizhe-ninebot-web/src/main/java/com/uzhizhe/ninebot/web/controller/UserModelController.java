package com.uzhizhe.ninebot.web.controller;

import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.permission.EOperateType;
import com.uzhizhe.ninebot.permission.Operate;
import com.uzhizhe.ninebot.service.user.UserModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Description UserModelController
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-24
 **/
@RestController
@RequestMapping("/ninebot")
@Slf4j
public class UserModelController {

    @Autowired
    private UserModelService userModelService;

    @Operate(EOperateType.VehicleManagement)
    @GetMapping(value = "/home/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto home(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String robotId) throws Exception {
        log.info("home, username:{}, password:{}, robotId:{}", username, password, robotId);

        return ResponseDto.success();
    }


    @PostMapping(value = "/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto addUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String robotId) throws Exception {
        log.info("home, username:{}, password:{}, robotId:{}", username, password, robotId);

        return ResponseDto.success();
    }

}
