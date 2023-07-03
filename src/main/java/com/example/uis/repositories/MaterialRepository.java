package com.example.uis.repositories;


import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {


    @Query(value = "select material " +
            "from Material material " +
            "where material.course.id = :courseId")
    List<Material> findAllByCourseId(@Param("courseId") Integer courseId);


    @Query(value = "select material.fullPath " +
            "from Material material " +
            "where material.id = :id")
    String fullPathById(@Param("id") Integer id);



    @Query(value = "select material.name " +
            "from Material material " +
            "where material.id = :id")
    String nameById(@Param("id") Integer id);
}
