package com.uzhizhe.ninebot.service.user.impl;

import com.uzhizhe.ninebot.dao.UserOperateModelRepository;
import com.uzhizhe.ninebot.entities.UserOperateModel;
import com.uzhizhe.ninebot.service.user.UserOperateModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc user model service impl
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-13
 */
@Service
public class UserOperateModelServiceImpl implements UserOperateModelService {

    @Autowired
    private UserOperateModelRepository userOperateModelRepository;

    @Override
    public UserOperateModel query(Integer id) {
        return userOperateModelRepository.findFirstById(id);
    }

    @Override
    public UserOperateModel query(String name) {
        return userOperateModelRepository.findFirstByOperateName(name);
    }

}
