package com.edikorce.FreeJobs.service;


import com.edikorce.FreeJobs.model.Role;
import com.edikorce.FreeJobs.model.User;
import com.edikorce.FreeJobs.repository.RoleRepo;
import com.edikorce.FreeJobs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;


    public User saveUser(MultipartFile file , User user) throws IOException {

        user.setUserImage(file.getBytes());

        return repo.save(user);
    }

    public List<User> getAllUsers() {

        return repo.findAll();
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

    @PostConstruct
    private void setInitialData() {

        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ADMIN");

        Role roleUser = new Role();
        roleUser.setRoleName("USER");

        Optional<Role> initialRole = roleRepo.findByRoleName("ADMIN");

        if (initialRole.isPresent()) {
            System.out.println("Data already present... skipping adding roles");
        } else {
            System.out.println("Data not present...adding roles");
            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);

        }


        Optional<User> initialUser = repo.findById(1L);

        if (initialUser.isPresent()) {

            System.out.println("Data Already Present ...Skipping adding admin user");

        } else {

            System.out.println("Data not present...adding user Admin with userPassword: 'Admin'....and userName: 'Admin'");

            User adminUser = new User();
            adminUser.setName("Admin");
            adminUser.setUserName("Admin");
            adminUser.setUserPassword(new BCryptPasswordEncoder().encode("Admin"));
            adminUser.setJoinedDate(new Date());
            adminUser.setActive(true);
            adminUser.setLocked(false);
            adminUser.setEmail("admin@email.com");
            Set<Role> roles = new HashSet<>();
            roles.add(roleAdmin);

            adminUser.setRoles(roles);
            repo.save(adminUser);
        }


    }
}


