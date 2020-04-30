package com.example.juc.sync.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 11:54
 * @Version 1.0
 **/
public class Consumer implements Runnable {
    private final Exchanger<Message> exchanger;

    public Consumer(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        Message msg = new Message(null);
        while (true) {
            try {
                Thread.sleep(1000);
                msg = exchanger.exchange(msg);
                System.out.println(Thread.currentThread().getName() + ": 消费了数据[" + msg.getV() + "]");
                msg.setV(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}