package com.uzhizhe.ninebot.service.user.impl;

import com.uzhizhe.ninebot.dao.UserRoleModelRepository;
import com.uzhizhe.ninebot.entities.UserRoleModel;
import com.uzhizhe.ninebot.service.user.UserRoleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc user model service impl
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-13
 */
@Service
public class UserRoleModelServiceImpl implements UserRoleModelService {


    @Autowired
    private UserRoleModelRepository userRoleModelRepository;

    @Override
    public UserRoleModel query(Integer id) {
        return userRoleModelRepository.findFirstById(id);
    }

    @Override
    public UserRoleModel query(String name) {
        return userRoleModelRepository.findFirstByRoleName(name);
    }

}
