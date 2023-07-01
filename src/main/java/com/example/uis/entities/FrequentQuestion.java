package com.example.uis.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "frequent_question")
public class FrequentQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
