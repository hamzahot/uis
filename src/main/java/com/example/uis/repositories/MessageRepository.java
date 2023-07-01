package com.example.uis.repositories;


import com.example.uis.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {


    @Query(value = "select message " +
            "from Message message " +
            "where message.course.id = :courseId")
    List<Message> findAllByCourseId(@Param("courseId") Integer courseId);

}
