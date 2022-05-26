package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {

    @Query("SELECT j FROM Job j WHERE j.user.id LIKE ?1")
    List<Job> getAllJobsByUserId(Long id);

}
