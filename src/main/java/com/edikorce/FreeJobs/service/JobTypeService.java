package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.JobType;
import com.edikorce.FreeJobs.repository.JobTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class JobTypeService {

    @Autowired
    private JobTypeRepo jobTypeRepo;

    public JobType saveJobType(JobType jobType) throws IOException {

        return jobTypeRepo.save(jobType);
    }

    public List<JobType> getAllJobTypes(){

        return jobTypeRepo.findAll();
    }

    @PostConstruct
    public void putInitialData(){

        Optional<JobType> dbJobType = jobTypeRepo.findById(1L);
        if (dbJobType.isEmpty()){
            System.out.println("Job types empty ...creating it");
            JobType jobType = new JobType();
            jobType.setName("Exchange");
            jobType.setDescription("pune qe ndrohet me dicka...jo me pagese");

            JobType jobType1 = new JobType();
            jobType1.setName("Paid");
            jobType1.setDescription("pune kundrejt nje pagese");

            jobTypeRepo.save(jobType);

            System.out.println("created job with name : " + jobType.getName());
            jobTypeRepo.save(jobType1);
            System.out.println("created job with name : " + jobType1.getName());
        }else{
            System.out.println("Job types exist");
        }

    }



}
