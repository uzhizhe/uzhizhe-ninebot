package com.uzhizhe.ninebot.dao;

import com.alibaba.fastjson.JSON;
import com.uzhizhe.ninebot.TestApplication;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.entities.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Desc user dao test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-08
 */
@Slf4j
public class UserDaoTest extends TestApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserModelRepository userModelRepository;

    @Test
    @Transactional
    public void test16() {


        User one = userRepository.findFirstById(1);

        System.out.println(JSON.toJSONString(one));

        one.setAge(222);


        User save = userRepository.save(one);
        userRepository.flush();
        //User user = userRepository.saveAndFlush(one);


    }

    @Test
    public void test46() {
        Date date = new Date();
        UserModel newuser = new UserModel();
        newuser.setUsername("wangzhuo");
        newuser.setPassword("123456");
        newuser.setType("User");
        newuser.setStatus(1);
        newuser.setRoleId(1);
        newuser.setCreator("liqingjiang");
        newuser.setCreateTime(date);
        newuser.setUpdateTime(date);
        UserModel save = userModelRepository.save(newuser);
        System.out.println(save);

        UserModel user = userModelRepository.findFirstById(2);
        System.out.println(user);

    }

}
