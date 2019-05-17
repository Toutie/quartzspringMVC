package com.etoak.job;

import com.etoak.serverce.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wang
 */
public class MvcJob implements Job {
    @Autowired
    EmailService emailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            emailService.senEmail();
    }
}
