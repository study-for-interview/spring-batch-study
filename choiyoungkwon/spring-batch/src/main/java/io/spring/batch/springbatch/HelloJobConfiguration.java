package io.spring.batch.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

//    @Bean
//    public Job myJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        Job myJob = new JobBuilder("myJob", jobRepository)
//                .start(oneStep(jobRepository, transactionManager))
//                .next(twoStep(jobRepository, transactionManager))
//                .build();
//        return myJob;
//    }
//
//    @Bean
//    public Step oneStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("oneStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    Thread.sleep(5000);
//                    log.info("-".repeat(80));
//                    log.info("hello spring batch!!");
//                    log.info("-".repeat(80));
//                    return RepeatStatus.FINISHED;
//                }, transactionManager) // or .chunk(chunkSize, transactionManager)
//                .build();
//    }
//    @Bean
//    public Step twoStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("oneStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("-".repeat(80));
//                    log.info("Two Step Running!!");
//                    log.info("-".repeat(80));
//                    return RepeatStatus.FINISHED;
//                }, transactionManager) // or .chunk(chunkSize, transactionManager)
//                .build();
//    }
}
