package com.example.uis.repositories;

import com.example.uis.dto.student.StudentQueryDTO;
import com.example.uis.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findAllByIsDeactivatedFalse();

    boolean existsByUsernameAndPassword(String username , String password);

    boolean existsByUsername(String username);

    @Query(value = "select student from Student student " +
            "join fetch student.roles " +
            "where student.username = :username")
    Student findWithRolesByUsername(@Param("username") String username);

    @Query("select student " +
            "from Student student " +
            "join student.courses course " +
            "where course.id = :courseId " +
            "and student.isDeactivated = false")
    List<Student> findAllByCourseIdAndIsDeactivatedFalse(Integer courseId);
}
