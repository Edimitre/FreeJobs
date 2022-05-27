package com.edikorce.FreeJobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] itemImage;


    private double value;

    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "itemList")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonBackReference(value = "items")
    private Job job;
}