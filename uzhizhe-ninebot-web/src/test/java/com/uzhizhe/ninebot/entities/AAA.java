package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-09
 */
@Data
public class AAA implements Serializable {

    private static final long serialVersionUID = -6530677369048987468L;
    private Integer id;
    private String name;
    private Boolean success;

    private Boolean gender;
    @JSONField(name = "creaet_time", format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;
    @JSONField(name = "update_time", format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;


}
