package com.uzhizhe.ninebot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Desc Bean 工具类
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-09
 */
public final class BeanUtil {

    private BeanUtil() {

    }


    public static void main(String[] args) {
        UserA userA = new UserA(1, "wangzhuo", true, new Date());
        UserB userB = new UserB();
        BeanUtils.copyProperties(userA, userB, "");
        System.out.println(userA);
        System.out.println(userB);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class UserA {
    private Integer id;
    private String name;
    private Boolean gender;
    private Date date;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class UserB {
    private Integer id;
    private String name;
    private Boolean gender;
    private Date date;
}
