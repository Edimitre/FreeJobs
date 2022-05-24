package com.edikorce.FreeJobs.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_table")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private JobType jobType;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte [] image;

}
