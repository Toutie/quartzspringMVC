package com.etoak.job;

import com.etoak.serverce.OrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author wang
 */
public class OrderJob extends QuartzJobBean {
    @Autowired
    OrderService orderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        orderService.deleteOrder();
    }
}
