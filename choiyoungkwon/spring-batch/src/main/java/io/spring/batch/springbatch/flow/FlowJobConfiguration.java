package io.spring.batch.springbatch.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
                .incrementer(new RunIdIncrementer())
                .start(oneStep(jobRepository, transactionManager))
                .next(decider())
                .from(decider()).on("ODD").to(oddStep(jobRepository, transactionManager))
                .from(decider()).on("EVEN").to(evenStep(jobRepository, transactionManager))
                .end()
//
//                .start(oneStep(jobRepository, transactionManager))
//                .on("COMPLETED")
//                .to(twoStep(jobRepository, transactionManager))
//                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new CustomDecider();
    }

    @Bean
    public Step oddStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("oneStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("ODD STEP !! ");
                    log.info("-".repeat(80));
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }

    @Bean
    public Step evenStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("oneStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("EVEN STEP !! ");
                    log.info("-".repeat(80));
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }

    @Bean
    public Step oneStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("oneStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("hello spring batch!!");
                    log.info("-".repeat(80));
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
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
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }

    @Bean
    public Step fourStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("fourStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("Four Step Running!!");
                    log.info("-".repeat(80));
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }
}
