package com.etoak.factory;

import org.quartz.spi.TriggerFiredBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author wang
 */
public class MvcJobFactory extends SpringBeanJobFactory {

    @Autowired
    AutowireCapableBeanFactory factory;


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = super.createJobInstance(bundle);
        factory.autowireBean(job);
        return job;
    }


}
