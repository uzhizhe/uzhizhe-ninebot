package com.uzhizhe.ninebot.web.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc user model vo
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-13
 */
public class UserModelVo implements Serializable {

    private static final long serialVersionUID = -3411927689341728309L;
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private Integer roleId;
    private String type;
    private String creator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;
}
