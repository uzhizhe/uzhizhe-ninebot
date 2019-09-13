package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Desc test bean
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-19
 */
@Slf4j
public class BeanTest {

    @Test
    public void test12() {
        String message = "{'from':2, 'to':5}";
        AbcTo bean = new AbcTo();
        bean.setId(100);
        bean.setName("uzz");
        bean.setMessage(message);

        System.out.println(JSON.toJSONString(bean));

    }


    @Test
    public void test28() {
        String message = "{\"id\":100,\"message\":\"{'from':2, 'to':5}\",\"name\":\"uzz\"}";


        AbcTo to = JSON.parseObject(message, AbcTo.class);
        log.info("ABC==>>>:" + JSON.toJSONString(to));

        Integer to1 = to.getTo();
        System.out.println(to1);
    }

}
