package com.edikorce.FreeJobs.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse implements Serializable {

    private User user;

    private String jwtToken;
}
