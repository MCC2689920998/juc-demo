package com.example.juc.sync.exchanger;

import lombok.Data;

/**
 * @Description
 * @Author MCC
 * @Date 2020/4/30 11:52
 * @Version 1.0
 **/
@Data
public class Message {
    private String V;
    public Message(String v){
        this.V = v;
    }
}
