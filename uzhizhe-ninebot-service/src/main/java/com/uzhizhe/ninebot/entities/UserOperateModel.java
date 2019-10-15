package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 用户操作表
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-10
 */
@Data
@Entity
@Table(name = "scooter_user_operate")
@org.hibernate.annotations.Table(appliesTo = "scooter_user_operate", comment = "用户操作表")
public class UserOperateModel implements Serializable {

    private static final long serialVersionUID = 8000796501914833584L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "operate_name", columnDefinition = "varchar(64) comment '操作中文名称'")
    private String operateName;
    @Column(nullable = false, name = "operate_en_name", columnDefinition = "varchar(64) comment '操作英文名称'")
    private String operateEnName;
    @Column(nullable = false, name = "permission_code", columnDefinition = "int(11) comment '操作权限值'")
    private Integer permissionCode;
    @Column(nullable = false, columnDefinition = "varchar(64) comment '创建者'")
    private String creator;
    @Column(nullable = false, name = "create_time", columnDefinition = "datetime comment '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;
    @Column(nullable = false, name = "update_time", columnDefinition = "datetime comment '更新时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;


}
