package com.example.juc.sync.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 11:51
 * @Version 1.0
 **/
public class Producer implements Runnable {
    private final Exchanger<Message> exchanger;

    public Producer(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        Message message = new Message(null);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);

                message.setV(String.valueOf(i));
                System.out.println(Thread.currentThread().getName() + ": 生产了数据[" + i + "]");

                message = exchanger.exchange(message);

                System.out.println(Thread.currentThread().getName() + ": 交换得到数据[" + String.valueOf(message.getV()) + "]");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
