package com.mlinyun.springboot3demo.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotifyService {

    public void sendMessageToUser(String message) {
        System.out.println(new Date() + " 通知方法被定时任务调用，开始发送通知: " + message);
        // to do something
        // 可以在该方法里写上逻辑代码，定时任务会定时调用该方法
    }

}
