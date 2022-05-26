package com.edikorce.FreeJobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            name = "jobs_items",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> itemList = new ArrayList<>();


    @Temporal(TemporalType.TIMESTAMP)
    private Date announcedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private double value;

    @Column(nullable = false)
    private boolean valid;

}
