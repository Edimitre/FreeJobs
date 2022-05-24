package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.Role;
import com.edikorce.FreeJobs.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepo repo;

    public Role saveRole(Role role){

        return repo.save(role);
    }

    public List<Role> getAllRoles(){

        return repo.findAll();
    }

    public Role getRoleByName(String roleName) throws Exception {
        Optional<Role> role = repo.findByRoleName(roleName);

        if (role.isPresent()){
           return role.get() ;
        }
        throw new Exception("Role not Found");
    }

}
