package com.example.uis.dto.frequent_question;


import lombok.Data;

@Data
public class FrequentQuestionEditDTO {

    private Integer id;
    private Integer courseId;
    private String question;
    private String answer;
}
