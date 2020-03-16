package com.uzhizhe.ninebot.java;

import com.uzhizhe.ninebot.utils.LDTUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Desc test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-09
 */
public class LocalDateTimeUtilTest {

    @Test
    public void test() {
        long currentMilli = LDTUtil.getCurrentMilli();
        System.out.println(currentMilli);
        LocalDateTime localDateTime = LDTUtil.milliToDate(currentMilli);
        String s = LDTUtil.dateToString(localDateTime);
        System.out.println(s);
        Date date = LDTUtil.LocalDateTimeToDate(localDateTime);
        String s1 = LDTUtil.dateToString(date);
        System.out.println(s1);

    }
}
