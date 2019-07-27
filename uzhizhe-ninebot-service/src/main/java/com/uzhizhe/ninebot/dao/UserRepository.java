package com.uzhizhe.ninebot.dao;

import com.uzhizhe.ninebot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {


}
