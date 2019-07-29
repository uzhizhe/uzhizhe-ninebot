package com.uzhizhe.ninebot.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qingjiang.li
 * @since 2019-07-03 2:16 PM
 */
@Slf4j
@Service
public class SysLoggerService {
    public boolean save(SysLoggerBo sysLoggerBo) {
        log.info(sysLoggerBo.toString());
        return true;
    }
}
