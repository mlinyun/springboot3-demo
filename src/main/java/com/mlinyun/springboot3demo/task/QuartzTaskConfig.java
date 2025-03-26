package com.mlinyun.springboot3demo.task;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzTaskConfig {

    @Bean
    public JobDetail simpleJobDetail() {
        return JobBuilder.newJob(QuartzTask.class).withIdentity("quartzTaskJob").storeDurably().build();
    }

    @Bean
    public Trigger simpleTrigger() {
        // 定义每 5s 执行一次任务
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        // 定义触发器
        return TriggerBuilder.newTrigger().forJob(simpleJobDetail()).withIdentity("quartzTaskJobTrigger").withSchedule(simpleScheduleBuilder).build();
    }

}
