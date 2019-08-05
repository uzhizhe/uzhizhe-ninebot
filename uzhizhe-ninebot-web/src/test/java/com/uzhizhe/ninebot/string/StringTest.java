package com.uzhizhe.ninebot.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uzhizhe.ninebot.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description StringTest
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-07-24
 **/
@Slf4j
public class StringTest {

    @Test
    public void test() {
        String str = "{\\\"code\\\\\":\\\"2050020\\\",\\\"id\\\":24334323,\\\"info\\\":\\\"extdepthr data error\\\",\\\"level\\\":\\\"1\\\",\\\"operator\\\":\\\"\\\",\\\"remark\\\":\\\"\\\",\\\"robotStatus\\\":\\\"\\\",\\\"uploadLog\\\":false}";

        str = str.replaceAll("\\\\", "");
        System.out.println();
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println();
        System.out.println(jsonObject);
    }

    @Test
    public void test1() {
        log.info("my name is {} {} {}", "li", "qing", "jiang");

        String s = null;
        User user = JSON.parseObject(s, User.class);
        System.out.println(user);
    }

    @Test
    public void test40() {
        String s1 = new String("abc") + new String("abc");
        String s1intern = s1.intern();
        String s2 = "abcabc";
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s1intern);//true
        System.out.println(s2 == s1intern);//true

    }


    @Test
    public void test55() {
        String s1 = new String("ABC");
        String s2 = "ABC";
        String s3 = s1.intern();
        String s4 = s1 + s2;
        String s5 = new String("ABC") + new String("ABC");
        String s6 = "ABCABC";
        String s7 = s5.intern();

        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);//true - false
        System.out.println(s2 == s3);//true
        System.out.println(s4 == s5);//false
        System.out.println(s4 == s6);//true - false
        System.out.println(s5 == s6);//false
        System.out.println(s6 == s7);//true
    }


    @Test
    public void test71() {

        Integer i1 = 110;
        Integer i2 = new Integer(110);
        int i3 = 110;

        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i2 == i3);

        System.out.println();
        Integer i4 = 520;
        Integer i5 = new Integer(520);
        int i6 = 520;

        System.out.println(i4 == i5);
        System.out.println(i4 == i6);
        System.out.println(i5 == i6);


    }
}
