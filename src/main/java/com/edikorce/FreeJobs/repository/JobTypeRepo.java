package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepo extends JpaRepository<JobType,Long> {



}
