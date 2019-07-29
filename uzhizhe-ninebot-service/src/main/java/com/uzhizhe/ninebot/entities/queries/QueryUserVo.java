package com.uzhizhe.ninebot.entities.queries;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc Query user vo
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-28
 */
@Data
public class QueryUserVo implements Serializable {
    private static final long serialVersionUID = -6756411737385890135L;
    private Integer id;
    private Integer gender;
    private String username;
    private Integer minAge;
    private Integer maxAge;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maxCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date minCreateTime;
    private Integer status;
    private Integer pageSize = 10;
    private Integer pageNumber = 1;


}
