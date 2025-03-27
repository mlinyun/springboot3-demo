package com.mlinyun.springboot3demo.task;

import com.mlinyun.springboot3demo.service.NotifyService;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@RequiredArgsConstructor
public class QuartzTask extends QuartzJobBean {

    private final NotifyService notifyService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        notifyService.sendMessageToUser("QuartzTask 定时任务开始执行");
    }

}
