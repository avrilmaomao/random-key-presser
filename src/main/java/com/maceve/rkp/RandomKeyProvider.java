package com.maceve.rkp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

@Slf4j
public class RandomKeyProvider {

    protected int[] NUM_KEYS = {
            VK_0,
            VK_1,
            VK_2,
            VK_3,
            VK_4,
            VK_5,
            VK_6,
            VK_7,
            VK_8,
            VK_9,
    };

    protected int[] ALPHABET_KEYS = {
            VK_A,
            VK_B,
            VK_C,
            VK_D,
            VK_E,
            VK_F,
            VK_G,
            VK_H,
            VK_I,
            VK_J,
            VK_K,
            VK_L,
            VK_M,
            VK_N,
            VK_O,
            VK_P,
            VK_Q,
            VK_R,
            VK_S,
            VK_T,
            VK_U,
            VK_V,
            VK_W,
            VK_X,
            VK_Y,
            VK_Z,

    };

    protected int[] SYMBOL_KEYS = {
          VK_COMMA, VK_PERIOD, VK_SLASH ,VK_SEMICOLON ,VK_EQUALS,VK_OPEN_BRACKET, VK_BACK_SLASH,VK_CLOSE_BRACKET,
    };

    protected int[] FUNCTION_KEYS = {
        VK_F1,VK_F2,VK_F3,VK_F4,VK_F5,VK_F6,VK_F7,VK_F8, VK_F9,VK_F10,VK_F11, VK_F12
    };

    protected int[] ASSIST_KEYS = {
        VK_CAPS_LOCK, VK_SHIFT, VK_ALT, VK_CONTROL,  VK_ALT
    };

    protected int[] ACTION_KEYS = {
        VK_ENTER, VK_ESCAPE, VK_WINDOWS,VK_TAB,VK_DELETE, VK_BACK_SPACE,VK_HOME, VK_END,VK_PAGE_UP, VK_PAGE_DOWN,
            VK_SPACE, VK_LEFT, VK_RIGHT, VK_UP, VK_DOWN
    };

    protected int[] allowedKeys = {};

    protected int[] ALL_KEYS;

    public RobotAction makeAction(){
        int randomKeyCode = fetchKeyCodes(1)[0];
        return new KeyPressAction(randomKeyCode);
    }

    public void allowNumKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, NUM_KEYS);
    }

    public void allowSymbolKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, SYMBOL_KEYS);
    }

    public void allowAssistKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, ASSIST_KEYS);
    }

    public void allowActionKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, ACTION_KEYS);
    }

    public void allowAll(){
        this.allowedKeys = ArrayUtils.addAll(NUM_KEYS, ALPHABET_KEYS);
        this.allowedKeys = ArrayUtils.addAll(ALL_KEYS, SYMBOL_KEYS);
        this.allowedKeys = ArrayUtils.addAll(ALL_KEYS, FUNCTION_KEYS);
        this.allowedKeys = ArrayUtils.addAll(ALL_KEYS, ASSIST_KEYS);
        this.allowedKeys = ArrayUtils.addAll(ALL_KEYS, ACTION_KEYS);
    }

    public void allowAlphabetKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, ALPHABET_KEYS);
    }

    public void allowFunctionKeys(){
        this.allowedKeys = ArrayUtils.addAll(this.allowedKeys, FUNCTION_KEYS);
    }



    public void clearAllowed(){
        this.allowedKeys = new int[]{0};
    }



     public int[] fetchKeyCodes(int num){
        assert this.allowedKeys.length > 0;
        return pickRandomN(this.allowedKeys, num);
     }

     @AllArgsConstructor
     public static class KeyPressAction implements RobotAction{
        private int keyCode;

         @Override
         public void act(Robot robot) {
             robot.keyPress(keyCode);
             robot.keyRelease(keyCode);
             log.info("key pressed: {}", KeyEvent.getKeyText(keyCode));
         }
     }


     private int[] pickRandomN(int[] arr, int num){
        int[] keys = new int[num];
        int arrLength = arr.length;
        for (int i = 0; i< num; i++){
            keys[i] = arr[RandomUtils.nextInt(0, arrLength)];
        }
        return keys;
     }

}
