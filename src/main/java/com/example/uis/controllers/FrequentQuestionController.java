package com.example.uis.controllers;


import com.example.uis.dto.frequent_question.FrequentQuestionCommandDTO;
import com.example.uis.dto.frequent_question.FrequentQuestionEditDTO;
import com.example.uis.dto.frequent_question.FrequentQuestionQueryDTO;
import com.example.uis.services.FrequentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frequent-question")
public class FrequentQuestionController {

    @Autowired
    private FrequentQuestionService frequentQuestionService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<FrequentQuestionQueryDTO>> findAllByCourseId(@PathVariable("id") Integer id)
    {
        List<FrequentQuestionQueryDTO> frequentQuestions = frequentQuestionService.findAllByCourseId(id);
        return new ResponseEntity<>(frequentQuestions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addFrequentQuestion(@RequestBody FrequentQuestionCommandDTO frequentQuestionCommandDTO)
    {
        frequentQuestionService.save(frequentQuestionCommandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("edit")
    public ResponseEntity<Void> updateQuestion(@RequestBody FrequentQuestionEditDTO frequentQuestionEditDTO)
    {
        frequentQuestionService.editQuestion(frequentQuestionEditDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        frequentQuestionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
