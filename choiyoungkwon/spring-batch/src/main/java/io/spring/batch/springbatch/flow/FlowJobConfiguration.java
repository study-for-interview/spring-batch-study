package io.spring.batch.springbatch.flow;

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
@RequiredArgsConstructor
@Configuration
public class FlowJobConfiguration {


    @Bean
    public Job flowJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("flowJob", jobRepository)
                .start(oneStep(jobRepository, transactionManager))
                .on("COMPLETED").to(threeStep(jobRepository, transactionManager))
                .from(oneStep(jobRepository, transactionManager))
                .on("FAILED").to(twoStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    public Step oneStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("oneStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("hello spring batch!!");
                    log.info("-".repeat(80));
                    throw new RuntimeException("FAIL");
//                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }
    @Bean
    public Step twoStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("twoStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("Two Step Running!!");
                    log.info("-".repeat(80));
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }

    @Bean
    public Step threeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("threeStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("Three Step Running!!");
                    log.info("-".repeat(80));
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }
}
