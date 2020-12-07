package com.practise.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableBatchProcessing
public class BatchLearningApplication {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("packageJob")
                .start(packageStep())
                .next(driveToAddress())
                .on("FAILED").to(storePackage())
                .from(driveToAddress()).on("*").to(deliverToCustomer())
                .end()
                .build();
    }

    @Bean
    public Step packageStep() {
        return stepBuilderFactory.get("packageStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        String item = chunkContext.getStepContext().getJobParameters().get("item").toString();
                        String date = chunkContext.getStepContext().getJobParameters().get("package_date").toString();
                        System.out.printf("Item %s was packed at date : %s%n", item, date);
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step driveToAddress() {
        //Toggle this flag for failure scenarios
        boolean GOT_LOST = true;
        return stepBuilderFactory.get("driveToAddressStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

                        if(GOT_LOST){
                            throw new RuntimeException("Got lost while locating the customer address");
                        }

                        System.out.print("Package was driven up to Customer Address");
                        return RepeatStatus.FINISHED;
                    }

                }).build();
    }

    @Bean
    public Step storePackage() {
        return stepBuilderFactory.get("storePackage")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.print("Could not find customer address. Storing the package.");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deliverToCustomer() {
        return stepBuilderFactory.get("delivertToCustomer")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.print("Package was successfully delivered to the customer at "+ new Date());
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchLearningApplication.class, args);
    }

}
