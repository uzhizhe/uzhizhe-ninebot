package com.uzhizhe.ninebot.common.enums.liftcenter;

/**
 * @Desc 发送到梯控中心的指令
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-12
 */
public enum ELiftCommand {
    LiftInnerCall,//电梯内部召梯
    LiftOuterCall,//电梯外面召梯
    LiftOpenDoor,//命令电梯开门
    LiftCloseDoor,//命令电梯关门
    LiftCancelCall, //取消电梯操控
}
