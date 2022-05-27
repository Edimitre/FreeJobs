package com.edikorce.FreeJobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinColumn(name = "job_type_id")
    private JobType jobType;


    @OneToMany(mappedBy = "job", cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "items")
    private List<Item> itemList = new ArrayList<>();


    @Temporal(TemporalType.TIMESTAMP)
    private Date announcedDate;

    @ManyToOne( cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "jobList")
    private User user;

    private double value;

    @Column(nullable = false)
    private boolean valid;

}
