package com.xyb.springBatch.itemReader.itemReaderOverride;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 自己实现ItemReader
 */
@Configuration
public class ItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderDemoJob() {

        return jobBuilderFactory.get("itemReaderDemoJob")
                .start(itemReaderDemoJobStep())
                .build();
    }

    @Bean
    public Step itemReaderDemoJobStep() {
        return stepBuilderFactory.get("itemReaderDemoJobStep")
                .<String, String>chunk(2)
                .reader(itemReaderDemoRead())
                .writer(list -> {
                    for(String item : list){
                        System.out.println(item + "...");
                    }
                })
                .build();
    }

    @Bean
    public MyReader<String> itemReaderDemoRead() {
        List<String> data = Arrays.asList("cat", "dog", "pig", "duck");
        return new MyReader<String>(data);
    }


}
