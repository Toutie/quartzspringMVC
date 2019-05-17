package com.etoak.controller;

import com.etoak.bean.JobBean;
import com.etoak.job.MvcJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 */
@RestController
public class JobController {

    @Autowired
    SchedulerFactoryBean factoryBean;

    @ResponseBody
    @PostMapping("/job")
    public Map<String,Object> createObject(@RequestBody JobBean jobBean){

        Map<String,Object> result = new HashMap<>();

        JobKey jobKey = new JobKey(jobBean.getJobName(),jobBean.getJobGroup());
        TriggerKey triggerKey = new TriggerKey(jobBean.getTriggerName(),jobBean.getTriggerGroup());

        Scheduler scheduler = factoryBean.getScheduler();
        try {
            //如何job存在了返回true
            boolean jobExists = scheduler.checkExists(jobKey);
            boolean triggerExists = scheduler.checkExists(triggerKey);

            if (jobExists || triggerExists){
                result.put("code",0);
                result.put("msg","任务已存在，不能重复创建");

                return  result;
            }
            //任务不存在 首先创建jobDetail 然后创建trigger 最后进行调度
            JobDetail jobDetail = JobBuilder
                    .newJob(MvcJob.class)
                    .withIdentity(jobKey)
                    .build();

            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(jobBean.getCronExpression())
                    ).build();
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            result.put("code",0);
            result.put("msg","任务已存在，不能重复创建");
           return result;
        }
        return result;
    }


    @GetMapping("/test")
    public void test(){
        System.out.println("手机访问了！");
    }
}
