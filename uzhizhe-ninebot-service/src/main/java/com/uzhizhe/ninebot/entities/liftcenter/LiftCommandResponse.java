package com.uzhizhe.ninebot.entities.liftcenter;

import lombok.Data;

import java.io.Serializable;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
@Data
public class LiftCommandResponse implements Serializable {
    private static final long serialVersionUID = -2755046176660058322L;
    private Integer code;
    private String message;
    private Object data;
}
