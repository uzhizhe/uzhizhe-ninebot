package com.uzhizhe.ninebot.service.timertask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Desc 定时任务Service
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-10-19
 */
@Service
@EnableScheduling
@Slf4j
public class TimerTaskService {

    /**
     * 开始地图质量评测:每天凌晨 03:12
     */
    @Scheduled(cron = "0 12 3 * * ?")
    private void startMapQualityEvaluation() {
        log.info("开始地图质量评测");

    }

}
