package com.uzhizhe.ninebot.service.user;

import com.uzhizhe.ninebot.entities.UserOperateModel;

public interface UserOperateModelService {

    UserOperateModel query(Integer id);

    UserOperateModel query(String name);


}
