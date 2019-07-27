package com.uzhizhe.ninebot.timer;

import org.junit.Test;

import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class TimerTest {

    private static final Integer num = 6;
    private volatile boolean enter = false;

    @Test
    public void test() throws Exception {

        openDoor("EVT5-11");
        openDoor("EVT5-12");

        Thread.sleep(5000);
        //System.out.println();
        openDoor("EVT5-11");

        Thread.sleep(25000);

    }

    private volatile Timer timer = new Timer();
    private volatile Map<String, TimerTask> taskMap = new ConcurrentHashMap<>();

    public void openDoor(String type) {
        TimerTask timerTask = taskMap.get(type);
        if (timerTask != null) {
            timerTask.cancel();
        }

        timerTask = new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (count < num) {
                    System.out.println(type);
                    if (Objects.equals(type, "EVT5-10")) {
                        //模拟异常
                        int i = 1 / 0;
                    }
                } else {
                    cancel();
                    taskMap.remove(type);
                }
                count++;
            }
        };
        taskMap.put(type, timerTask);
        timer.schedule(timerTask, 0, 2000);

    }

}
