package com.uzhizhe.ninebot.service;

import com.monker.common.result.PageResult;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.entities.queries.QueryUserVo;

public interface UserService {

    User add(User user);

    User findById(Integer id);

    PageResult<User> findAll(QueryUserVo queryUserVo);


}
