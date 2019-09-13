package com.uzhizhe.ninebot.entities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Desc test bean
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-19
 */
@Slf4j
@Data
public class AbcTo implements Serializable {

    private static final long serialVersionUID = -327547677737120348L;
    private Integer id;
    private String name;
    private Object message;


    public Integer getTo() {
        String message = getMessage().toString();
        JSONObject jsonObject = JSON.parseObject(message);
        return jsonObject.getInteger("to");
    }

}
