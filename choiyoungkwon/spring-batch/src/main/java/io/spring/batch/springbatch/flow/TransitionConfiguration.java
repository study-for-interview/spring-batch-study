package io.spring.batch.springbatch.flow;


import io.spring.batch.springbatch.listener.PassCheckingListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
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
public class TransitionConfiguration {

    @Bean
    public Job transitionTestJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("transitionText", jobRepository)
                .start(step1(jobRepository, transactionManager))
                .on("FAILED")
                .to(step2(jobRepository, transactionManager))
                .on("PASS")
                .stop()
                .from(step2(jobRepository, transactionManager))
                .on("COMPLETED")
                .to(step3(jobRepository, transactionManager))
                .end()
                .build();
    }


    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("oneStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("hello spring batch!!");
                    log.info("-".repeat(80));
                    contribution.setExitStatus(ExitStatus.FAILED);
//                    throw new RuntimeException("FAIL");
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("twoStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("-".repeat(80));
                    log.info("Two Step Running!!");
                    log.info("-".repeat(80));
                    return RepeatStatus.FINISHED;
                }, transactionManager) // or .chunk(chunkSize, transactionManager)
                .listener(new PassCheckingListener())
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
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
}
