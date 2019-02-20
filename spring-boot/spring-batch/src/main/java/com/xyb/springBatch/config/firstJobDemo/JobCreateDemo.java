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
 * job创建步骤
 */
@Configuration
public class JobCreateDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job jobCreate1(){
        //多个step顺序执行
        return jobBuilderFactory.get("jobCreate1")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();

        //条件执行：1如果成功，运行2，2如果成功运行3，3运行完结束。
//        return jobBuilderFactory.get("JobCreateDemo--jobCreate1On")
//                .start(step1())
//                .on("COMPLETED").to(step2())
//                .from(step2()).on("COMPLETED").to(step3())
//                .from(step3()).end()
//                .build();

        //条件执行：1如果成功，运行2，当2运行成功，直接运行失败，后面就不运行了。
//        return jobBuilderFactory.get("JobCreateDemo--jobCreate1Fail")
//                .start(step1())
//                .on("COMPLETED").to(step2())
//                .from(step2()).on("COMPLETED").fail()
//                .from(step3()).end()
//                .build();

        //条件执行：1如果成功，运行2，当2运行成功，重启，一般用于测试
//        return jobBuilderFactory.get("JobCreateDemo--jobCreate1Fail")
//                .start(step1())
//                .on("COMPLETED").to(step2())
//                .from(step2()).on("COMPLETED").stopAndRestart()
//                .from(step3()).end()
//                .build();
    }


    @Bean
    public Step step1() {

        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobCreateDemo--jobCreate1--step1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2() {

        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobCreateDemo--jobCreate1--step2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step3() {

        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobCreateDemo--jobCreate1--step3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
