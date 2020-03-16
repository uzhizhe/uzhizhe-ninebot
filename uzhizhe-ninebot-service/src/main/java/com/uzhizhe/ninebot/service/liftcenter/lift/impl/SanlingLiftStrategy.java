package com.uzhizhe.ninebot.service.liftcenter.lift.impl;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;
import com.uzhizhe.ninebot.entities.liftcenter.LiftTrip;
import com.uzhizhe.ninebot.service.liftcenter.lift.LiftStrategy;
import com.uzhizhe.ninebot.utils.LiftCommandUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
@Slf4j
public class SanlingLiftStrategy implements LiftStrategy, Serializable {
    private static final long serialVersionUID = 5091000584959880606L;

    @Override
    public LiftCommandResponse callLift(LiftTrip liftTrip) {
        Integer liftFrom = liftTrip.getLiftFrom();
        Integer liftTo = liftTrip.getLiftTo();
        String robotId = liftTrip.getRobotId();
        String liftId = liftTrip.getLiftId();
        Integer liftNo = liftTrip.getLiftNo();
        String liftTripStatus = liftTrip.getLiftTripStatus();
        if (liftTripStatus.equalsIgnoreCase("")) {
            log.info("机器人{}召梯, 三菱电梯{}: 从【{}】到【{}】", robotId, liftId, liftFrom, liftTo);
        }
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
