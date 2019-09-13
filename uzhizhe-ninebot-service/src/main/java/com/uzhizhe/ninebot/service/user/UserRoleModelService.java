package com.uzhizhe.ninebot.service.user;

import com.uzhizhe.ninebot.entities.UserRoleModel;

public interface UserRoleModelService {

    UserRoleModel query(Integer id);

    UserRoleModel query(String username);


}
