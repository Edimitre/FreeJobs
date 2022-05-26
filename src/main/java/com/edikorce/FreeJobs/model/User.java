package com.edikorce.FreeJobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String email;

    @Column(unique = true,nullable = false)
    private String userName;

    @Column(unique = true,nullable = false)
    private String userPassword;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] userImage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Job> jobList = new ArrayList<>();

    private boolean active;

    private boolean locked;


}