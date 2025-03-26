package com.mlinyun.springboot3demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    /**
     * cron 表达式，通过表达式来配置任务执行时间
     * 表示每 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled1() {
        System.out.println(new Date() + " scheduled1 定时任务执行");
    }

    /**
     * fixedRate：此参数定义了任务开始的间隔。例如，如果你设置 fixedRate = 5000，
     * 那么任务将会每 5 秒开始一次。这意味着任务的执行时间可能会超过5秒，因为它取决于任务的执行时间
     * 每 5s 执行一次
     */
    @Scheduled(fixedRate = 5000)
    public void scheduled2() {
        System.out.println(new Date() + " scheduled2 定时任务执行");
    }

    /**
     * fixedDelay：此参数定义了任务执行的间隔。例如，如果你设置fixedDelay = 5000，
     * 那么任务将会在每次执行完毕后等待5秒再开始下一次执行。这意味着任务的开始时间可能会晚于预期，
     * 因为它取决于上一次任务的执行时间
     */
    @Scheduled(fixedDelay = 5000)
    public void scheduled3() {
        System.out.println(new Date() + " scheduled3 定时任务执行");
    }

}
