package com.uzhizhe.ninebot.service.user;

import com.uzhizhe.ninebot.entities.UserModel;

public interface UserModelService {

    UserModel query(Integer id);

    UserModel query(String name);


}
