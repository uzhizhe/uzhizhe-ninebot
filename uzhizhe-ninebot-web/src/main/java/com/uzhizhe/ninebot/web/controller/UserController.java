package com.uzhizhe.ninebot.web.controller;

import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.service.UserService;
import com.uzhizhe.ninebot.web.logger.annotations.SysLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description UserController
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-24
 **/
@RestController
@RequestMapping("/ninebot")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @SysLogger("添加用户")
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object addUser(@RequestBody User user) throws Exception {
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        User result = userService.add(user);
        return ResponseDto.success(result);
    }

    @SysLogger("FindOne 用户")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<User> findOne(@PathVariable("id") Integer id) throws Exception {
        User user = userService.findById(id);
        return ResponseDto.success(user);
    }

    @SysLogger("FindAll 用户")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<List<User>> findAll() throws Exception {
        List<User> users = userService.findAll();
        return ResponseDto.success(users);
    }


}
