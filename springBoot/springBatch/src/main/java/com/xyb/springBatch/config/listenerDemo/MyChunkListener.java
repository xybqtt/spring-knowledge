package com.xyb.springBatch.config.listenerDemo;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext){
        System.out.println(chunkContext.getStepContext().getStepName() + "before");
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext){
        System.out.println(chunkContext.getStepContext().getStepName() + "after");
    }
}
