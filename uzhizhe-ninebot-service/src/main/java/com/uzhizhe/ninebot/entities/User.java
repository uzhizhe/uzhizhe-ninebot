package com.uzhizhe.ninebot.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ninebot_user")
@Accessors(chain = true)
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -2189704378510291669L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32, nullable = false)
    private String username;
    @Column(name = "nick_name", length = 32, nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String info;
    @Column(columnDefinition = "int", length = 1, nullable = false)
    private Integer gender;
    @Column(columnDefinition = "int", length = 3, nullable = false)
    private Integer age;
    @Column(columnDefinition = "int", length = 2, nullable = false)
    private Integer status;
    @Column(name = "update_time", nullable = false)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Column(name = "create_time", nullable = false)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
