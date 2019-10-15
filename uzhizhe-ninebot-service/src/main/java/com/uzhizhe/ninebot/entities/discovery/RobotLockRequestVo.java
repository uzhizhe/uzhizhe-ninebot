package com.uzhizhe.ninebot.entities.discovery;

import lombok.Data;

import java.io.Serializable;

/**
 * @Desc 接入方解锁 VO
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-26
 */
@Data
public class RobotLockRequestVo implements Serializable {
    private static final long serialVersionUID = -8762626967469612173L;
    private String vehicleNum;//RobotId
    private Long orderNo;//唯一标识 : 订单ID

}
