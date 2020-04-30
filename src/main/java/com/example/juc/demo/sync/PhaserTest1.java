package com.example.juc.demo.sync;

import java.util.concurrent.Phaser;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 9:41
 * @Version 1.0
 **/
public class PhaserTest1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            phaser.register();                  // 注册各个参与者线程
            System.out.println("register");
           new Thread(new Task(phaser), "Thread-" + i).start();
        }
    }
}

class Task implements Runnable {
    private final Phaser phaser;

    Task(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        int i = phaser.arriveAndAwaitAdvance();     // 等待其它参与者线程到达
        // do something
        System.out.println(Thread.currentThread().getName() + ": 执行完任务，当前phase =" + i + "");
    }
}