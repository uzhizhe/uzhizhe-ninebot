package com.uzhizhe.ninebot.common.enums.liftcenter;

/**
 * @Desc LiftTrip 的状态
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-12
 */
public enum ELiftTripStatus {
    Init,           //召梯前初始化状态
    CallLift,       //召梯时
    LiftArriveFrom, //初始楼层到达时
    RobotInLift,    //机器人确认进电梯时
    LiftArriveTo,   //目标楼层到达时
    RobotOutLift    //机器人确认出电梯时
}
