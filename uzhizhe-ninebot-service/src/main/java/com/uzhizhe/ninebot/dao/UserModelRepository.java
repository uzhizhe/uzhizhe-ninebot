package com.uzhizhe.ninebot.dao;

import com.uzhizhe.ninebot.entities.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

    Page<UserModel> findAll(Specification<UserModel> specification, Pageable pageable);

    List<UserModel> findAll(Specification<UserModel> specification);

    /**
     * find first by id
     *
     * @param id
     * @return
     */
    UserModel findFirstById(Integer id);

    /**
     * findFirstByUsernameAndStatusAndType
     *
     * @param username
     * @param status
     * @param type
     * @return
     */
    UserModel findFirstByUsernameAndStatusAndType(String username, int status, String type);


}
