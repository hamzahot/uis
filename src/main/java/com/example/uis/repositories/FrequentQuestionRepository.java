package com.example.uis.repositories;

import com.example.uis.entities.FrequentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FrequentQuestionRepository extends JpaRepository<FrequentQuestion, Integer> {


    @Query(value = "select frequentQuestion " +
            "from FrequentQuestion frequentQuestion " +
            "where frequentQuestion.course.id = :courseId")
    List<FrequentQuestion> findAllByCourseId(@Param("courseId") Integer courseId);

}
