package com.xyb.springBatch.config.jobLauncherDemo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * JobLanucher的作用是为了让job被动触发，而不是启动项目时主动触发。
 */
@Configuration
public class JobLanucherDemo implements StepExecutionListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String, JobParameter> parameter;

    @Bean
    public Job jobLanucherDemoJob(){
        return jobBuilderFactory.get("jobLanucherDemoJob")
                .start(jobLanucherDemoJobStep())
                .build();
    }

    @Bean
    public Step jobLanucherDemoJobStep() {
        return stepBuilderFactory.get("jobLanucherDemoJobStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(parameter.get("msg").getValue());
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameter = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
