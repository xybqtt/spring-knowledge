package com.xyb.springBatch.config.nestedJobDemo;

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

@Configuration
public class ChildJob2 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job childJobTwo(){
        //多个step顺序执行
        return jobBuilderFactory.get("childJobTwo")
                .start(childJob2Step3())
                .next(childJob2Step4())
                .build();

    }


    @Bean
    public Step childJob2Step3() {

        return stepBuilderFactory.get("childJob2Step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJob2Step3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step childJob2Step4() {

        return stepBuilderFactory.get("childJob2Step4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJob2Step4");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
