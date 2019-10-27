package com.uzhizhe.ninebot.dao;

import com.uzhizhe.ninebot.entities.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    @Modifying
    @Query("update UserModel u set u.username = :username where u.id = :id")
    int updateUser(@Param(value = "username") String username, @Param(value = "id") Integer id);
}
