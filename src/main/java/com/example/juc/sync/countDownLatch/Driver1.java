package com.example.juc.sync.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 14:03
 * @Version 1.0
 **/
public class Driver1 {

    private static final int N = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch compsignal = new CountDownLatch(N);
        for (int i = 0; i < N; ++i) {
            new Thread(new Worker(compsignal)).start();
        }
        compsignal.await();       // 主线程等待其它N个线程完成
        doSomething();
    }

    public static void doSomething() {
        System.out.println("doSomething");
    }
}

class Worker implements Runnable {
    private final CountDownLatch compSignal;

    Worker(CountDownLatch compSignal) {
        this.compSignal = compSignal;
    }

    @Override
    public void run() {
        doWork();
        compSignal.countDown(); //每个线程做完自己的事情后，就将计数器减去1
    }

    void doWork() {
        System.out.println("doWork");
    }
}