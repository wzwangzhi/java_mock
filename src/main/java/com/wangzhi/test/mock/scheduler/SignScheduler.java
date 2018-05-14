package com.wangzhi.test.mock.scheduler;

import com.wangzhi.test.mock.service.SignService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignScheduler {
    @Resource
    SignService signService;

    @Scheduled( cron = "0 0 * * * *" )
    public void signAll(){
        System.out.println("正在执行签到。。。");
        signService.signAll();
    }
}
