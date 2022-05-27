package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {


    @Query("SELECT i FROM Item i WHERE i.job.user.id LIKE ?1")
    List<Item> getAllItemsByUserId(Long id);

}
