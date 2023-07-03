package com.example.uis.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "full_path")
    private String fullPath;

    private String description;

    @Column(name = "is_reviewed")
    private Boolean isReviewed = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String filename;
}
