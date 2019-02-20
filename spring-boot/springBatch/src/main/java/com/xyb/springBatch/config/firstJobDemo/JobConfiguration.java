package com.xyb.springBatch.config.firstJobDemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 步骤：加@Configuration注解。
 * 1.注入创建Job和Step的工厂类。
 * 2.创建Job和Step。注意方法要用@Bean修饰，且public
 * 3.创建step有两种方式tasklet和chunk
 * 4.tasklet记得返回正确值。
 *
 */
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("HelloWorldJob")
                .start(step())
                .build();
    }


    @Bean
    public Step step() {

        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("HelloWorld");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


}
