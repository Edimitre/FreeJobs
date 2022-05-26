package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {



}
