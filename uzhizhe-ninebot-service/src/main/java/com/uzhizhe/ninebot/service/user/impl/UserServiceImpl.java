package com.uzhizhe.ninebot.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.monker.common.result.PageResult;
import com.uzhizhe.ninebot.dao.UserRepository;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.entities.queries.QueryUserVo;
import com.uzhizhe.ninebot.logger.annotations.SysLogger;
import com.uzhizhe.ninebot.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("#{'${test.id.list}'.split(',')}")
    private List<String> idList;

    //@Value("${test.id.list}")
    //private String idList;

    @Override
    public User add(User user) {
        User save = userRepository.save(user);
        log.info("user : {}", JSON.toJSONString(user));
        log.info("save : {}", JSON.toJSONString(save));
        return save;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        log.info("idList:{}", idList);
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    @SysLogger("Find all user by page")
    public PageResult<User> findAll(QueryUserVo queryUserVo) {

        Specification<User> specification = getSpecification(queryUserVo);

        Sort sort = Sort.by(Sort.Order.desc("gender"), Sort.Order.asc("age"));

        int page = queryUserVo.getPageNumber() <= 0 ? 0 : queryUserVo.getPageNumber() - 1;
        int pageSize = queryUserVo.getPageSize() <= 0 ? 10 : queryUserVo.getPageSize();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<User> userPage = userRepository.findAll(specification, pageable);
        return PageResult.instance(pageSize, page + 1,
                Long.valueOf(userPage.getTotalElements()).intValue(),
                userPage.getContent());
    }

    private Specification<User> getSpecification(QueryUserVo userVo) {
        Specification<User> specification = new Specification<User>() {
            private static final long serialVersionUID = -5528860199898071819L;

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (userVo.getId() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("id"), userVo.getId()));
                }
                if (userVo.getMinAge() != null) {
                    predicateList.add(criteriaBuilder.ge(root.get("age"), userVo.getMinAge()));
                }
                if (userVo.getMaxAge() != null) {
                    predicateList.add(criteriaBuilder.le(root.get("age"), userVo.getMaxAge()));
                }
                if (userVo.getGender() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("gender"), userVo.getGender()));
                }
                if (userVo.getStatus() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), userVo.getStatus()));
                }
                if (userVo.getUsername() != null) {
                    predicateList.add(criteriaBuilder.like(root.get("username").as(String.class), "%" + userVo.getUsername() + "%"));
                }
                //jtodo createTime min max
                if (userVo.getMinCreateTime() != null) {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), userVo.getMinCreateTime()));
                }
                if (userVo.getMaxCreateTime() != null) {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), userVo.getMaxCreateTime()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }


}
