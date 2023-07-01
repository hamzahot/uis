package com.example.uis.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
