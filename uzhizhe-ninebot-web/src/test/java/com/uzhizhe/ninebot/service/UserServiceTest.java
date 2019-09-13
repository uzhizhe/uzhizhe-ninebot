package com.uzhizhe.ninebot.service;

import com.uzhizhe.ninebot.TestApplication;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Desc user service test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-12
 */
@Slf4j
public class UserServiceTest extends TestApplication {

    @Autowired
    private UserService userService;

    @Test
    public void test21() {
        User user = userService.findById(1);
        System.out.println(user);
    }

}
