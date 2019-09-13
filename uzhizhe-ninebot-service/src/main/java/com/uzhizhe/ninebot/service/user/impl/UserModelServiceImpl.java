package com.uzhizhe.ninebot.service.user.impl;

import com.uzhizhe.ninebot.dao.UserModelRepository;
import com.uzhizhe.ninebot.entities.UserModel;
import com.uzhizhe.ninebot.permission.EUserStatus;
import com.uzhizhe.ninebot.permission.EUserType;
import com.uzhizhe.ninebot.service.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc user model service impl
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-13
 */
@Service
public class UserModelServiceImpl implements UserModelService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public UserModel query(Integer id) {
        return null;
    }

    @Override
    public UserModel query(String username) {
        UserModel userModel = userModelRepository.findFirstByUsernameAndStatusAndType(username, EUserStatus.Normal.getId(), EUserType.User.name());
        return userModel;
    }
}
