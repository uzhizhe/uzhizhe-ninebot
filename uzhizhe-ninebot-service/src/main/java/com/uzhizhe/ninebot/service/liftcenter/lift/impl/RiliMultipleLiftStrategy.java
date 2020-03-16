package com.uzhizhe.ninebot.service.liftcenter.lift.impl;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;
import com.uzhizhe.ninebot.entities.liftcenter.LiftTrip;
import com.uzhizhe.ninebot.service.liftcenter.lift.LiftStrategy;
import com.uzhizhe.ninebot.utils.LiftCommandUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc RiliMultipleLiftStrategy
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
@Slf4j
public class RiliMultipleLiftStrategy implements LiftStrategy {

    private static final long serialVersionUID = -8235731431370515133L;

    @Override
    public LiftCommandResponse callLift(LiftTrip liftTrip) {
        Integer liftFrom = liftTrip.getLiftFrom();
        Integer liftTo = liftTrip.getLiftTo();
        String robotId = liftTrip.getRobotId();
        String liftId = liftTrip.getLiftId();
        log.info("机器人{}召梯, 日立群控电梯{}: 从【{}】到【{}】", robotId, liftId, liftFrom, liftTo);
        return LiftCommandUtil.success();
    }

    @Override
    public LiftCommandResponse closeDoor(LiftTrip liftTrip) {
        Integer liftFrom = liftTrip.getLiftFrom();
        Integer liftTo = liftTrip.getLiftTo();
        String robotId = liftTrip.getRobotId();
        String liftId = liftTrip.getLiftId();
        log.info("机器人{}关门, 日立群控电梯{}: 从【{}】到【{}】", robotId, liftId, liftFrom, liftTo);
        return LiftCommandUtil.success();
    }

    @Override
    public LiftCommandResponse openDoor(LiftTrip liftTrip) {
        Integer liftFrom = liftTrip.getLiftFrom();
        Integer liftTo = liftTrip.getLiftTo();
        String robotId = liftTrip.getRobotId();
        String liftId = liftTrip.getLiftId();
        log.info("机器人{}开门, 日立群控电梯{}: 从【{}】到【{}】", robotId, liftId, liftFrom, liftTo);
        return LiftCommandUtil.success();
    }

    @Override
    public LiftCommandResponse cancelLift(LiftTrip liftTrip) {
        Integer liftFrom = liftTrip.getLiftFrom();
        Integer liftTo = liftTrip.getLiftTo();
        String robotId = liftTrip.getRobotId();
        String liftId = liftTrip.getLiftId();
        log.info("机器人{}取消电梯, 日立群控电梯{}: 从【{}】到【{}】", robotId, liftId, liftFrom, liftTo);
        return LiftCommandUtil.success();
    }
}
