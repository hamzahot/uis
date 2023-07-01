package com.example.uis.services;


import com.example.uis.dto.frequent_question.FrequentQuestionCommandDTO;
import com.example.uis.dto.frequent_question.FrequentQuestionQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.entities.FrequentQuestion;
import com.example.uis.mappers.FrequentQuestionMapper;
import com.example.uis.repositories.FrequentQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequentQuestionService {

    @Autowired
    private FrequentQuestionRepository frequentQuestionRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FrequentQuestionMapper frequentQuestionMapper;

    public List<FrequentQuestionQueryDTO> findAllByCourseId(Integer id) {
        return frequentQuestionRepository.findAllByCourseId(id)
            .stream().map(frequentQuestionMapper :: entityToQueryDto).toList();
    }

    public void save(FrequentQuestionCommandDTO frequentQuestionCommandDTO)
    {
        Course course = courseService.findById(frequentQuestionCommandDTO.getCourseId());

        FrequentQuestion frequentQuestion = frequentQuestionMapper.commandDtoToEntity(frequentQuestionCommandDTO);
        frequentQuestion.setCourse(course);

        frequentQuestionRepository.save(frequentQuestion);
    }
}
