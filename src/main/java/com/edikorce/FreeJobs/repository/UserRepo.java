package com.edikorce.FreeJobs.repository;


import com.edikorce.FreeJobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user_table WHERE userName = :name", nativeQuery = true)
    List<User> findByName(String name);


    @Query(value = "SELECT * FROM user_table WHERE user_name = :username", nativeQuery = true)
    Optional<User> findByUserName(String username);

}
