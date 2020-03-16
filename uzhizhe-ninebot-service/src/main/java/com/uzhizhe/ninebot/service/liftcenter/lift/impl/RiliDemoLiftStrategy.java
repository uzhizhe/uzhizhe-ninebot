package com.uzhizhe.ninebot.service.liftcenter.lift.impl;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;
import com.uzhizhe.ninebot.entities.liftcenter.LiftTrip;
import com.uzhizhe.ninebot.service.liftcenter.lift.LiftStrategy;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-12
 */
public class RiliDemoLiftStrategy implements LiftStrategy {

    private static final long serialVersionUID = 3016778324957385785L;

    @Override
    public LiftCommandResponse callLift(LiftTrip liftTrip) {
        return null;
    }

    @Override
    public LiftCommandResponse closeDoor(LiftTrip liftTrip) {
        return null;
    }

    @Override
    public LiftCommandResponse openDoor(LiftTrip liftTrip) {
        return null;
    }

    @Override
    public LiftCommandResponse cancelLift(LiftTrip liftTrip) {
        return null;
    }
}
