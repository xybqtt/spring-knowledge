package com.xyb.springBatch.config.jobParamDemo;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import java.util.Map;

public class ParametersStepListener implements StepExecutionListener {


    private Map<String, JobParameter> parameterMap;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameterMap = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    public JobParameter get(String key) {
        return parameterMap.get(key);
    }

    public void set(String key, JobParameter jobParameter) {
        parameterMap.put(key, jobParameter);
    }

}
