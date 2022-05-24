package com.edikorce.FreeJobs.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class JwtRequest implements Serializable {

    private String userName;

    private String userPassword;
}
