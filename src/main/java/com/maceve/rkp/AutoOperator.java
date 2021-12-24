package com.maceve.rkp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
public class AutoOperator {
    Robot robot;

    private final int MIN_INTERVAL = 1000;
    private final int MAX_INTERVAL = 5000;




    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            log.error("failed to initialise Robot", e);
        }
    }

    public void randomKeyPress(int miliSeconds){
        int totalPressed = 0;
        RandomKeyProvider keyProvider = new RandomKeyProvider();
        keyProvider.allowAlphabetKeys();
        keyProvider.allowAssistKeys();
        keyProvider.allowNumKeys();
        keyProvider.allowSymbolKeys();

        Instant start = Instant.now();
        while (true){
            Instant current = Instant.now();
            if(ChronoUnit.MILLIS.between(start, current) > miliSeconds){
                log.info("key pressing time reached:{}, totalPressed:{}",miliSeconds, totalPressed);
                break;
            }
            RobotAction keyAction = keyProvider.makeAction();
            keyAction.act(robot);

            totalPressed ++;

            sleep(RandomUtils.nextInt(MIN_INTERVAL, MAX_INTERVAL));
        }
    }

    public void randomMouseOps(int miliSeconds){
        int totalPressed = 0;
        RandomMouseProvider mouseProvider = new RandomMouseProvider();
        Instant start = Instant.now();
        while (true){
            Instant current = Instant.now();
            if(ChronoUnit.MILLIS.between(start, current) > miliSeconds){
                log.info("mouse operation time reached:{}, operations:{}",miliSeconds, totalPressed);
                break;
            }
            RobotAction keyAction = mouseProvider.generateAction();
            keyAction.act(robot);
            totalPressed ++;

            sleep(RandomUtils.nextInt(MIN_INTERVAL, MAX_INTERVAL));
        }
    }


    public void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
