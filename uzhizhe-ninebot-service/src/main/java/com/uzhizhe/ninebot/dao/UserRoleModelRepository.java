package com.uzhizhe.ninebot.dao;

import com.uzhizhe.ninebot.entities.UserModel;
import com.uzhizhe.ninebot.entities.UserRoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleModelRepository extends JpaRepository<UserRoleModel, Integer> {

    Page<UserRoleModel> findAll(Specification<UserModel> specification, Pageable pageable);

    List<UserRoleModel> findAll(Specification<UserModel> specification);

    /**
     * find first by id
     *
     * @param id
     * @return
     */
    UserRoleModel findFirstById(Integer id);

    /**
     * findFirstByRoleName
     *
     * @param roleName
     * @return
     */
    UserRoleModel findFirstByRoleName(String roleName);
}
