package com.uzhizhe.ninebot.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    }
}
