package com.edikorce.FreeJobs.service;


import com.edikorce.FreeJobs.model.User;
import com.edikorce.FreeJobs.repository.RoleRepo;
import com.edikorce.FreeJobs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;


    public User saveUser(MultipartFile file , User user) throws IOException {

        if(file !=null && !file.isEmpty()){
            user.setUserImage(file.getBytes());
        }

        if (!user.getJobList().isEmpty()){
            user.getJobList().forEach(job -> {
                job.getItemList().forEach(item -> {
                    item.setJob(job);
                    item.setUser(user);
                });
            });
        }
        return repo.save(user);
    }

    public List<User> getAllUsers() {

        return repo.findAll();
    }

    public User getById(Long id) throws Exception {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new Exception("User not Found");
        }

    }




    public User getUserByUserName(String userName) throws Exception {
        Optional<User> user = repo.findByUserName(userName);

        if (user.isPresent()) {
            return user.get();
        } else
            throw new Exception("User not Found");

    }



    public void deleteUserById(Long id) {

        repo.deleteById(id);
    }


}


