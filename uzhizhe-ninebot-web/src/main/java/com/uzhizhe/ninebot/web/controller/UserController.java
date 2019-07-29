package com.uzhizhe.ninebot.web.controller;

import com.monker.common.result.PageResponseDto;
import com.monker.common.result.PageResult;
import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.entities.queries.QueryUserVo;
import com.uzhizhe.ninebot.logger.annotations.SysLogger;
import com.uzhizhe.ninebot.web.vo.UserVo;
import com.uzhizhe.ninebot.webservice.UserWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    private UserWebService userWebService;

    @SysLogger("添加用户")
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto addUser(@RequestBody UserVo userVo) throws Exception {
        userWebService.add(userVo);
        return ResponseDto.success();
    }

    @SysLogger("FindOne 用户")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<UserVo> findOne(@PathVariable("id") Integer id) throws Exception {
        UserVo userVo = userWebService.findById(id);
        return ResponseDto.success(userVo);
    }

    @SysLogger("FindAll")
    @PostMapping(value = "/users/find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<UserVo> findAll(@RequestBody QueryUserVo queryUserVo) {
        PageResult<UserVo> pageResult = userWebService.findAll(queryUserVo);
        return PageResponseDto.success(pageResult);
    }
}
