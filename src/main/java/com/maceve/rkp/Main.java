package com.maceve.rkp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int defaultDuration = 1000 * 3600 * 8;

        if(args.length == 0){
            log.info("no duration specified,using default:{} ms", defaultDuration);
        }
        int operationDuration = args.length == 0 ? defaultDuration : Integer.parseInt(args[0]);

        log.info("start auto operation , close important documents or applications");

        Thread.sleep(6000);

        Thread keyThread = new Thread(()->new AutoOperator().randomKeyPress(operationDuration));
        Thread mouseThread = new Thread(()->new AutoOperator().randomMouseOps(operationDuration));

        keyThread.start();
        mouseThread.start();;
        log.info("operation started");
        keyThread.join();
        mouseThread.join();
        log.info("operation finished");

    }
}
