package com.edikorce.FreeJobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne( cascade = {CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;
}