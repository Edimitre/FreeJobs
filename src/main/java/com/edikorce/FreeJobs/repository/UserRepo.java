package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {





    @Query("SELECT u FROM User u WHERE u.userName LIKE ?1")
    Optional<User> findByUserName(String username);

}
