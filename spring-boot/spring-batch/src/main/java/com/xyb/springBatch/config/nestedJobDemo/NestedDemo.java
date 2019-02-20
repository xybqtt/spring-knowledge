package com.xyb.springBatch.config.nestedJobDemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 父job，启动时为了只启动父job，需要配置
 * spring.batch.job.names = parentJob
 */
@Configuration
public class NestedDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // 注意此处注入的是job名，并不是具体的类
    @Autowired
    private Job childJobTwo;

    @Autowired
    private Job childJobOne;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job parentJobs(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        //多个job顺序执行
        return jobBuilderFactory.get("parentJobs")
                .start(nestedJobchildJob2(jobRepository, platformTransactionManager))
                .next(nestedJobchildJob2(jobRepository, platformTransactionManager))
                .build();

    }

    //返回的是Job类型的Step，特殊的Step
    @Bean
    public Step nestedJobchildJob2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobStepBuilder(new StepBuilder("nestedJobchildJob2"))
                .job(childJobTwo)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Step nestedJobchildJob1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobStepBuilder(new StepBuilder("nestedJobchildJob1"))
                .job(childJobOne)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }


}
