package com.uzhizhe.ninebot.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Desc CityInfo
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-13
 */
@Data
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 4401573639317632581L;
    private Integer id;
    private String name;
}
