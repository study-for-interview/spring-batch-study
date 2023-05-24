package io.spring.batch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class JobLauncherController {

    private final Job job;
    private final JobLauncher jobLauncher;

    private final TaskExecutorJobLauncher taskExecutorJobLauncher;

//    @PostMapping("/batch")
//    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addDate("date", new Date())
//                .toJobParameters();
//        List<String> test = new ArrayList<>(){
//
//            @Override
//            public boolean isEmpty() {
//                return true;
//            }
//
//        };
//        List<String> test1 = test.stream().filter(s -> s.equals("test")).map(s -> s.toUpperCase()).collect(Collectors.toList());
//        taskExecutorJobLauncher.run(job, jobParameters);
//
//        return "batch completed";
//    }
}
