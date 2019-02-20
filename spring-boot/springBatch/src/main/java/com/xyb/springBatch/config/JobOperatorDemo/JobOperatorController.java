package com.xyb.springBatch.config.JobOperatorDemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 此处让controller触发Job，要添加start-web依赖
 */
//@RestController
public class JobOperatorController {

    /**
     * 在另一个类里面注入的bean
     */
    @Autowired
    private JobOperator jobOperator;

    //    @RequestMapping("/job2/{msg}")
    public String jobRun1(String msg) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobInstanceAlreadyExistsException, NoSuchJobException {

        //启动任务，同时传参数，参数1为job名(不需注入，jobLanucher要注入)，参数2直接写成k=v就行，不需使用JobParameters
        jobOperator.start("jobOperatorDemoJob", "msg=" + msg);

        return null;
    }
}
