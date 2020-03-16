package com.uzhizhe.ninebot.utils;

import com.uzhizhe.ninebot.entities.liftcenter.LiftCommandResponse;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-12-11
 */
public final class LiftCommandUtil {
    private LiftCommandUtil() {

    }

    public static LiftCommandResponse success() {
        LiftCommandResponse response = new LiftCommandResponse();
        response.setCode(200);
        response.setMessage("SUCCESS");
        return response;
    }
}
