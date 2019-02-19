package com.xyb.springBatch.config.jobParamDemo;

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
 * 给Job传递参数，本质是给Step传递参数，所以给step传递参数即可。使用step
 * 级别监听方式给step传递参数。在配置中设置参数：xxx=xxx
 */
@Configuration
public class ParametersDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    ParametersStepListener parametersStepListener;

    @Bean
    public Job parameterJob3(){
        return jobBuilderFactory.get("parameterJob3")
                .start(parameterJobStep())
                .build();
    }

    @Bean
    public Step parameterJobStep() {
        this.parametersStepListener = new ParametersStepListener();
        return stepBuilderFactory.get("parameterJobStep")
                .listener(parametersStepListener)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("参数：" + parametersStepListener.get("info"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
