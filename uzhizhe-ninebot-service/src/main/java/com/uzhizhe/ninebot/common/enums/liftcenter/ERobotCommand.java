package com.uzhizhe.ninebot.common.enums.liftcenter;

/**
 * @Desc 机器人发送到电梯中心的事件
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-12
 */
public enum ERobotCommand {
    //召梯指令
    CallLift,
    //机器人确认进电梯
    RobotInLift,
    //机器人确认出电梯
    RobotOutLift,
    //取消电梯
    CancelLift,
    //关闭电梯门
    CloseDoor,
    //打开电梯门
    OpenCoor,
    //补召电梯
    ReplenishCallLift,
}
