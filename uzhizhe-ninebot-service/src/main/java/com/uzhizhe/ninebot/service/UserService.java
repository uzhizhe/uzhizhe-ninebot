package com.uzhizhe.ninebot.service;

import com.uzhizhe.ninebot.entities.User;

import java.util.List;

public interface UserService {

    User add(User user);

    User findById(Integer id);

    List<User> findAll();

}
