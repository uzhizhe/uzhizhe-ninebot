package com.uzhizhe.ninebot.entities.liftcenter;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc LiftTrip
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
@Data
public class LiftTrip implements Serializable {

    private static final long serialVersionUID = 3368631384483077269L;
    private Integer id; //自增ID
    private String taskId; // 任务唯一ID
    private String liftTripId;//乘梯TripId
    private String liftTripStatus;//乘梯状态
    private String liftId;//电梯ID
    private Integer liftNo;//电梯号
    private Integer liftFrom;//初始楼层
    private Integer liftTo;//目标楼层
    private String robotId;//机器人ID
    private Boolean liftGroupControl;//电梯是否是群控电梯
    private Date startTime;//trip 开始时间: 召梯时间
    private Date updateTime;//trip 更新时间: 就是确认出梯时间
    private String fromEnv;//来自那个环境: alpha alpha2 alpha3 dev dev2 dev3 demo liftcenter:表示从电梯中心后台操控
    private String fromRobot;// Robot: 机器人 Person: 人为操作

}
