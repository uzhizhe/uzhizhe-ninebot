package com.uzhizhe.ninebot;

import com.alibaba.fastjson.JSONObject;
import com.uzhizhe.ninebot.entities.AAA;
import org.junit.Test;

import java.util.*;

/**
 * @Desc java test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-23
 */
public class JavaTest {


    @Test
    public void test15() {
        Map<String, List<Integer>> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        map.put("numbers", list);

        System.out.println(map);


        List<Integer> list1 = map.get("numbers");
        list1.add(4);

        System.out.println(map);
    }

    @Test
    public void test40() {
        int power = 333;


        int p1 = 1;
        int p2 = 2;
        int p4 = 4;
        int p8 = 8;
        int p16 = 16;
        int p32 = 32;
        int p64 = 64;
        int p128 = 128;
        int p256 = 256;

        int i = power & p1;
        int i2 = power & p2;
        int i4 = power & p4;
        int i8 = power & p8;
        int i16 = power & p16;
        int i32 = power & p32;
        int i64 = power & p64;
        int i128 = power & p128;
        int i256 = power & p256;

        System.out.println(i);
        System.out.println(i2);
        System.out.println(i4);
        System.out.println(i8);
        System.out.println(i16);
        System.out.println(i32);
        System.out.println(i64);
        System.out.println(i128);
        System.out.println(i256);


    }

    @Test
    public void test78() {
        AAA aaa = new AAA();
        aaa.setId(100);
        aaa.setName("Wahaha");
        aaa.setGender(true);
        aaa.setSuccess(false);
        Date time = new Date();
        aaa.setCreateTime(time);
        aaa.setUpdateTime(time);

        System.out.println(JSONObject.toJSONString(aaa));

    }

}
