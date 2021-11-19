package com.maceve.rkp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.awt.Robot;
import java.awt.event.MouseEvent;

@Slf4j
public class RandomMouseProvider {


    public RobotAction generateAction(){
        RobotAction action;
        int actionNum = RandomUtils.nextInt(0, 9);
        if(actionNum < 3){
            action = new MouseWheelAction(60 - RandomUtils.nextInt(0, 120));
        }else{
            int mouseButton = actionNum < 3 ? MouseEvent.BUTTON2_DOWN_MASK : MouseEvent.BUTTON1_DOWN_MASK;
            action = new MouseClickAction(RandomUtils.nextInt(0,600), RandomUtils.nextInt(0, 800), mouseButton);
        }
        return action;
    }




    @AllArgsConstructor
    public static class MouseWheelAction implements RobotAction{
        private int scrolls;

        @Override
        public void act(Robot robot) {
            robot.mouseWheel(scrolls);
        }
    }

    @AllArgsConstructor
    public static class MouseClickAction implements RobotAction{
        private int x;
        private int y;
        private int button;

        @Override
        public void act(Robot robot) {
            robot.mouseMove(x, y);
            robot.mousePress(button);
            robot.mouseRelease(button);
            log.info("mouse pressed {}, at ({},{})", button, x, y);
        }
    }
}
