package com.edikorce.FreeJobs.controller;


import com.edikorce.FreeJobs.model.Job;
import com.edikorce.FreeJobs.service.ItemService;
import com.edikorce.FreeJobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@CrossOrigin
public class JobController {


    @Autowired
    private JobService jobService;

    @Autowired
    private ItemService itemService;




    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/register")
    public Job registerJob(@RequestBody Job job) throws Exception {

        job.setValid(true);

        return jobService.saveJob(job);

    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all/user")
    public List<Job> getAllUserJobs(@RequestParam(value = "id") Long id) throws Exception {
        return jobService.getAllJobsByUserId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public List<Job> getAllJobs() throws Exception {

        return jobService.getAllJobs();

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteJobById(@RequestParam(value = "id") Long id) throws Exception {

        jobService.deleteJobById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
