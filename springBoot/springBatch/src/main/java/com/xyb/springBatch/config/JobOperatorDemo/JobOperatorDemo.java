package com.xyb.springBatch.config.JobOperatorDemo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * JobOperator封装了JobLanucher
 */
@Configuration
public class JobOperatorDemo implements StepExecutionListener, ApplicationContextAware {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Map<String, JobParameter> parameterMap;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobExplorer jobExplorer;

    /**
     * 用于注册job，因为jobOperator.start("jobOperatorDemoJob", "msg=" + msg);的第一个参数只是字符串，
     * 并没有注册Job
     */
    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobOperator jobOperator(){
        SimpleJobOperator simpleJobOperator = new SimpleJobOperator();

        simpleJobOperator.setJobLauncher(jobLauncher);
        //为了将字符串k=v转换为JobParameters
        simpleJobOperator.setJobParametersConverter(new DefaultJobParametersConverter());
        simpleJobOperator.setJobRepository(jobRepository);
        simpleJobOperator.setJobExplorer(jobExplorer);
        simpleJobOperator.setJobRegistry(jobRegistry);

        return simpleJobOperator;
    }


    @Bean
    public Job jobOperatorDemoJob(){
        return jobBuilderFactory.get("jobOperatorDemoJob")
                .start(jobLanucherDemoJobStep())
                .build();
    }

    @Bean
    public Step jobLanucherDemoJobStep() {
        return stepBuilderFactory.get("jobOperatorDemoJobStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(parameterMap.get("msg").getValue());
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistry() throws Exception {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();

        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        jobRegistryBeanPostProcessor.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
        jobRegistryBeanPostProcessor.afterPropertiesSet();
        return jobRegistryBeanPostProcessor;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameterMap = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
