package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.Role;
import com.edikorce.FreeJobs.model.User;
import com.edikorce.FreeJobs.repository.RoleRepo;
import com.edikorce.FreeJobs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    public Role saveRole(Role role){

        return roleRepo.save(role);
    }

    @PostConstruct
    private void setInitialData() {

        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ADMIN");

        Role roleUser = new Role();
        roleUser.setRoleName("USER");

        Optional<Role> initialRole = roleRepo.findByRoleName("ADMIN");

        if (initialRole.isPresent()) {
            System.out.println("Roles already present... skipping adding roles");
        } else {
            System.out.println("Data not present...adding roles");
            roleRepo.save(roleAdmin);
            System.out.println("added role : " + roleAdmin.getRoleName());
            roleRepo.save(roleUser);
            System.out.println("added role : " + roleUser.getRoleName());

        }


        Optional<User> initialUser = userRepo.findById(1L);

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
            userRepo.save(adminUser);
        }


    }

    public List<Role> getAllRoles(){

        return roleRepo.findAll();
    }

    public Role getRoleByName(String roleName) throws Exception {
        Optional<Role> role = roleRepo.findByRoleName(roleName);

        if (role.isPresent()){
           return role.get() ;
        }
        throw new Exception("Role not Found");
    }

}
