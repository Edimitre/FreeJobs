package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.Utils.JwtUtil;
import com.edikorce.FreeJobs.model.JwtRequest;
import com.edikorce.FreeJobs.model.JwtResponse;
import com.edikorce.FreeJobs.model.User;
import com.edikorce.FreeJobs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> mUser = repo.findByUserName(username);


        if (mUser.isPresent()){
            User user = mUser.get();

            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPassword(), getAuthorities(user));

        }else{

            throw new UsernameNotFoundException("UserName is not Valid");
        }
    }


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {

        String userName = jwtRequest.getUserName();

        String userPassword = jwtRequest.getUserPassword();

        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken =  jwtUtil.generateToken(userDetails);

        User user = repo.findByUserName(userName).get();

        return new JwtResponse(user, newGeneratedToken);
    }


    private void authenticate(String username ,String userPassword) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userPassword));
        }catch(DisabledException e){
            throw new Exception("User is Disabled");
        }catch(BadCredentialsException b){
            throw new Exception("Bad credentials from user");
        }


    }


    private Set getAuthorities(User user){

        Set authorities = new HashSet();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;

    }


    public String getEncodedPassword(String password){

        return passwordEncoder.encode(password);
    }

}
