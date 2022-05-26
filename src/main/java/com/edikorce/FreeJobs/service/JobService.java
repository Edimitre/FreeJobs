package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.Job;
import com.edikorce.FreeJobs.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    public Job saveJob(Job job){

        if (!job.getItemList().isEmpty()){
            job.getItemList().forEach(item -> {
                item.setJob(job);
            });
        }
        return jobRepo.save(job);
    }

    public List<Job> getAllJobsByUserId(Long id){

        return jobRepo.getAllJobsByUserId(id);
    }
    public List<Job> getAllJobs(){

        return jobRepo.findAll();
    }


    public void deleteJobById(Long id) throws Exception {

        Optional<Job> jobOptional = jobRepo.findById(id);

        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            if (!job.getItemList().isEmpty()){
                job.getItemList().forEach(item -> {
                    item.setJob(null);
                });
            }
            jobRepo.delete(job);
        }else{
            throw new Exception("Job Not Found");
        }


    }


}
