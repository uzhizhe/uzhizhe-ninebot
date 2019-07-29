package com.uzhizhe.ninebot.dao.cache;

import com.alibaba.fastjson.JSON;
import com.monker.common.utils.CollectionUtil;
import com.uzhizhe.ninebot.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc Redis cache service
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-29
 */
@Slf4j
@Service
public class RedisCacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String USER_PRE = "ninebot.user.";

    public void saveOrUpdate(User user) {
        redisTemplate.opsForValue().set(assembleUserKey(user.getId()), user2Str(user));
    }

    public void delete(Integer id) {
        redisTemplate.delete(assembleUserKey(id));
    }

    public User findById(Integer id) {
        String s = redisTemplate.opsForValue().get(assembleUserKey(id));
        return JSON.parseObject(s, User.class);
    }

    public List<User> findAll(List<Integer> idList) {
        List<User> result = new ArrayList<>();
        List<String> idStrList = idList.stream().map(this::assembleUserKey).collect(Collectors.toList());
        List<String> userStrList = redisTemplate.opsForValue().multiGet(idStrList);
        if (CollectionUtil.notBlank(userStrList)) {
            result = userStrList.stream().map(user -> JSON.parseObject(user, User.class)).collect(Collectors.toList());
        }
        return result;
    }


    private String assembleUserKey(Integer id) {
        return new StringBuilder(USER_PRE).append(id).toString();
    }

    private String user2Str(User user) {
        return JSON.toJSONString(user);
    }

}
