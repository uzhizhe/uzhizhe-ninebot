package com.uzhizhe.ninebot.service.liftcenter.lift;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;
import com.uzhizhe.ninebot.entities.liftcenter.LiftTrip;

import java.io.Serializable;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
public interface LiftStrategy extends Serializable {

    LiftCommandResponse callLift(LiftTrip liftTrip);

    LiftCommandResponse closeDoor(LiftTrip liftTrip);

    LiftCommandResponse openDoor(LiftTrip liftTrip);

    LiftCommandResponse cancelLift(LiftTrip liftTrip);

}
