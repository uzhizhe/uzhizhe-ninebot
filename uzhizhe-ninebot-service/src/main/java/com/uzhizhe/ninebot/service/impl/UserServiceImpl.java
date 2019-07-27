package com.uzhizhe.ninebot.service.impl;

import com.uzhizhe.ninebot.dao.UserRepository;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        User save = userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
