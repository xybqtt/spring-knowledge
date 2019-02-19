package com.xyb.springBatch.config.flowDemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Flow是多个step的集合，可以被多个job使用
 */
@Configuration
public class JobFlowDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job flowDemoJob(){
        //多个step顺序执行
        return jobBuilderFactory.get("flowDemoJob")
                .start(flowDemoFlow())
                .next(flowDemoStep3())
                .end()
                .build();

    }

    // 创建Flow对象，指明含有那些step
    @Bean
    public Flow flowDemoFlow(){
        return new FlowBuilder<Flow>("flowDemoFlow")
                .start(flowDemoStep1())
                .next(flowDemoStep2())
                .build();
    }


    @Bean
    public Step flowDemoStep1() {

        return stepBuilderFactory.get("flowDemoStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobFlowDemo--flowDemoStep1--step1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flowDemoStep2() {

        return stepBuilderFactory.get("flowDemoStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobFlowDemo--flowDemoStep2--step2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flowDemoStep3() {

        return stepBuilderFactory.get("flowDemoStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobFlowDemo--flowDemoStep3--step3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


}
