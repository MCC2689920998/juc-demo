package com.example.juc.sync.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 11:54
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new Consumer(exchanger), "消费者-t1");
        Thread t2 = new Thread(new Producer(exchanger), "生产者-t2");

        t1.start();
        t2.start();
    }

}
