package com.uzhizhe.ninebot.liftcenter;

import com.uzhizhe.ninebot.entities.liftcenter.LiftTrip;
import com.uzhizhe.ninebot.service.liftcenter.content.LiftContext;
import com.uzhizhe.ninebot.service.liftcenter.lift.impl.SanlingLiftStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
@Slf4j
public class LiftCenterTest {


    @Test
    public void test15() {
        SanlingLiftStrategy sanlingLiftCenter = new SanlingLiftStrategy();
        LiftContext liftCenterContent = new LiftContext(sanlingLiftCenter);

        LiftTrip liftTrip = new LiftTrip();


    }
}
