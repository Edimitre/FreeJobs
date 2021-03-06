package com.edikorce.FreeJobs.controller;


import com.edikorce.FreeJobs.model.JwtRequest;
import com.edikorce.FreeJobs.model.JwtResponse;
import com.edikorce.FreeJobs.model.Role;
import com.edikorce.FreeJobs.model.User;
import com.edikorce.FreeJobs.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ItemService itemService;
    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        return jwtService.createJwtToken(jwtRequest);
    }


    @GetMapping("/getProfile")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public User getUserByName(@RequestParam(value = "userName") String userName) throws Exception {
        return userService.getUserByUserName(userName);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam(value = "user") String user,@RequestParam(value = "imgFile") MultipartFile imgFile) throws Exception {

        User user1 = new ObjectMapper().readValue(user, User.class);

        Role role = roleService.getRoleByName("USER");


        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user1.setRoles(roleSet);

        String encodedPassword = new BCryptPasswordEncoder().encode(user1.getUserPassword());
        user1.setUserPassword(encodedPassword);

        user1.setJoinedDate(new Date());

        user1.setLocked(false);
        user1.setActive(true);



        return new ResponseEntity<>(userService.saveUser(imgFile,user1),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity deleteUserById(@RequestParam(value = "id") Long id){

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
