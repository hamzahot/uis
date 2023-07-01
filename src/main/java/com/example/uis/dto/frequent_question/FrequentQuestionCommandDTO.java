package com.example.uis.dto.frequent_question;


import lombok.Data;

@Data
public class FrequentQuestionCommandDTO {

    private Integer courseId;
    private String question;
    private String answer;
}
