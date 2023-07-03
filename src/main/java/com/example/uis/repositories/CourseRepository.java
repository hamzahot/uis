package com.example.uis.repositories;

import com.example.uis.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("select course " +
            "from Course course " +
            "where course.active = 1")
    List<Course> findAllActive();
}
