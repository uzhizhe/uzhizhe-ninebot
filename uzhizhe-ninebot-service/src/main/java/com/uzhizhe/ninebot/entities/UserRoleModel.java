package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 用户角色表
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-10
 */
@Data
@Entity
@Table(name = "scooter_user_role")
@org.hibernate.annotations.Table(appliesTo = "scooter_user_role", comment = "用户角色表")
public class UserRoleModel implements Serializable {

    private static final long serialVersionUID = 6863661770589331607L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, name = "role_name", columnDefinition = "varchar(64) comment '角色名称,Robot:0, Admin, Operator, User, Visitor'")
    private String roleName;
    @Column(nullable = false, name = "role_permission_code", columnDefinition = "int(11) comment '角色权限值'")
    private Integer rolePermissionCode;
    @Column(nullable = false, columnDefinition = "varchar(64) comment '创建者'")
    private String creator;
    @Column(nullable = false, name = "create_time", columnDefinition = "datetime comment '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;
    @Column(nullable = false, name = "update_time", columnDefinition = "datetime comment '更新时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;


}
