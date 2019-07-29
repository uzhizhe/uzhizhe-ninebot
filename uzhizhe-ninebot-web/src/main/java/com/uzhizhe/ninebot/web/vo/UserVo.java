package com.uzhizhe.ninebot.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc User vo
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-28
 */
@Data
@Builder
public class UserVo implements Serializable {

    private static final long serialVersionUID = 153851565898876656L;
    private Integer id;
    private String username;
    private String nickName;
    private String info;
    private Integer age;
    private Integer gender;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
