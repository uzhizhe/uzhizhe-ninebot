package com.uzhizhe.ninebot.webservice;

import com.monker.common.result.PageResult;
import com.uzhizhe.ninebot.entities.User;
import com.uzhizhe.ninebot.entities.queries.QueryUserVo;
import com.uzhizhe.ninebot.service.user.UserService;
import com.uzhizhe.ninebot.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc User web service
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-28
 */
@Service
public class UserWebService {

    @Autowired
    private UserService userService;

    public void add(UserVo userVo) {
        //校验参数

        //拼装参数
        Date date = new Date();
        User user = User.builder()
                .username(userVo.getUsername())
                .nickName(userVo.getNickName())
                .gender(userVo.getGender())
                .status(userVo.getStatus())
                .age(userVo.getAge())
                .info(userVo.getInfo())
                .createTime(date)
                .updateTime(date)
                .build();

        //添加数据
        userService.add(user);

    }

    public UserVo findById(Integer id) {
        User user = userService.findById(id);
        return userConvertor(user);
    }

    public PageResult<UserVo> findAll(QueryUserVo queryUserVo) {
        //校验参数

        //查询数据
        PageResult<User> pageResult = userService.findAll(queryUserVo);

        //拼装数据返回
        Collection<User> data = pageResult.getData();
        List<UserVo> voList = data.stream().map(this::userConvertor).collect(Collectors.toList());
        return PageResult.instance(pageResult.getPageSize(), pageResult.getPageNumber(),
                pageResult.getTotalNumber(), voList);
    }

    /**
     * user convertor userVo
     *
     * @param user user
     * @return userVo
     */
    private UserVo userConvertor(User user) {
        return UserVo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickName(user.getNickName())
                .age(user.getAge())
                .gender(user.getGender())
                .info(user.getInfo())
                .status(user.getStatus())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }

}
