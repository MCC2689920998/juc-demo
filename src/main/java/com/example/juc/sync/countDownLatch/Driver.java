package com.example.juc.sync.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 13:59
 * @Version 1.0
 **/
public class Driver {
    private static final int N = 10;

    public static void main(String[] args) {
        CountDownLatch switcher = new CountDownLatch(1);
        for (int i = 0; i < N; ++i) {
            new Thread(new Worker1(switcher)).start();
        }
        doSomething();
        switcher.countDown();       // 主线程开启开关
    }

    public static void doSomething() {
        System.out.println("doSomething");
    }

}

class Worker1 implements Runnable {
    private final CountDownLatch startSignal;

    Worker1(CountDownLatch startSignal) {
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();    //所有执行线程在此处等待开关开启
            doWork();
        } catch (InterruptedException ex) {
        }
    }
    void doWork() {
        System.out.println("doWork");
    }
}
