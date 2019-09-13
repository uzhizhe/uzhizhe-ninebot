package com.uzhizhe.ninebot.dao;

import com.uzhizhe.ninebot.entities.UserModel;
import com.uzhizhe.ninebot.entities.UserOperateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserOperateModelRepository extends JpaRepository<UserOperateModel, Integer> {

    Page<UserOperateModel> findAll(Specification<UserModel> specification, Pageable pageable);

    List<UserOperateModel> findAll(Specification<UserModel> specification);

    /**
     * find first by id
     *
     * @param id
     * @return
     */
    UserOperateModel findFirstById(Integer id);

    /**
     * findFirstByOperateName
     *
     * @param operateName
     * @return
     */
    UserOperateModel findFirstByOperateName(String operateName);
}
