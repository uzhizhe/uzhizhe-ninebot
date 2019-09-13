package com.uzhizhe.ninebot.cache;

import com.alibaba.fastjson.JSON;
import com.monker.common.utils.StringUtil;
import com.uzhizhe.ninebot.TestApplication;
import com.uzhizhe.ninebot.dao.cache.RedisCacheService;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Desc Redis cache user test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-29
 */
@Slf4j
public class RedisUserCacheTest extends TestApplication {

    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private UserService userService;

    @Test
    public void test() {

        User user = userService.findById(1);
        soutJson(user);
        redisCacheService.saveOrUpdate(user);

        User redisUser = redisCacheService.findById(1);
        soutJson(redisUser);

    }

    private void soutJson(Object o) {
        System.out.println("Test output:");
        String s = JSON.toJSONString(o);
        String formart = StringUtil.JsonFormart(s);
        System.out.println(formart);
    }

}
