package org.sheamus.concurrency.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("1111");
            }
        };
        // 延时多长时间执行，并且间隔多长时间执行一次
        timer.scheduleAtFixedRate(timerTask, 10, 1000);
    }
}
