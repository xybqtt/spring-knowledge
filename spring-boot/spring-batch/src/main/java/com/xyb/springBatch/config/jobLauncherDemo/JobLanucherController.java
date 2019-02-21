package com.xyb.springBatch.config.jobLauncherDemo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 此处让controller触发Job，要添加start-web依赖
 */
//@RestController
public class JobLanucherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobLanucherDemoJob;

    //    @RequestMapping("/job/{msg}")
    public String jobRun1(String msg) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        //将接收到的参数值传给任务
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("msg", msg)
                .toJobParameters();

        //启动任务，并把参数传给任务
        JobExecution jobExecution = jobLauncher.run(jobLanucherDemoJob, jobParameters);
        if ("COMPLETED".equals(jobExecution.getExitStatus().getExitCode()))
            return "执行成功";
        else
            return "执行失败";
    }
}
