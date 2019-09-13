package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 用户信息表
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-10
 */
@Data
@Entity
@Table(name = "scooter_user")
@org.hibernate.annotations.Table(appliesTo = "scooter_user", comment = "用户信息表")
public class UserModel implements Serializable {


    private static final long serialVersionUID = -1743955620937112460L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(64) comment '用户名称'")
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(64) comment '用户密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "int(4) comment '用户状态:1正常 2冻结'")
    private Integer status;

    @Column(nullable = false, columnDefinition = "int(11) comment '角色ID'")
    private Integer roleId;

    @Column(nullable = false, columnDefinition = "varchar(32) comment '角色类型:User,Robot'")
    private String type;

    @Column(nullable = false, columnDefinition = "varchar(64) comment '创建者名称:System或者具体某用户名'")
    private String creator;

    @Column(nullable = false, name = "create_time", columnDefinition = "datetime comment '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    @Column(nullable = false, name = "update_time", columnDefinition = "datetime comment '更新时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;


}
