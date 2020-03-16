package com.uzhizhe.ninebot.service.liftcenter.content;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;
import com.uzhizhe.ninebot.service.liftcenter.lift.LiftStrategy;
import com.uzhizhe.ninebot.utils.LiftCommandUtil;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
public class LiftContext {

    private static final long serialVersionUID = -7422750274999496225L;

    private LiftStrategy liftStrategy;

    public LiftContext(LiftStrategy liftStrategy) {
        this.liftStrategy = liftStrategy;
    }

    public void setLiftCenter(LiftStrategy liftStrategy) {
        this.liftStrategy = liftStrategy;
    }

    public LiftCommandResponse callLift(String robotId, String liftId, Integer liftFrom, Integer liftTo) {


        return LiftCommandUtil.success();
    }

    public LiftCommandResponse confirmEnterLift(String robotId) {

        return LiftCommandUtil.success();
    }


}
