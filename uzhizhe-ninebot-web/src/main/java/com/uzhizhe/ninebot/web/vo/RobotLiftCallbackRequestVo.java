package com.uzhizhe.ninebot.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2020-02-14
 */
@Data
public class RobotLiftCallbackRequestVo implements Serializable {

    private static final long serialVersionUID = -5106558072076396793L;
    private String data;
    private String sign;
    private String merchantId;

}
