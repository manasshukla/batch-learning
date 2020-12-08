package com.practise.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

public class CorrectIemDecider implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        String result = new Random().nextFloat() > .70f ? "CORRECT" : "INCORRECT";
        return new FlowExecutionStatus(result);
    }
}
