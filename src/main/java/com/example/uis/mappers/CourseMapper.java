package com.example.uis.mappers;


import com.example.uis.dto.course.CourseCommandDTO;
import com.example.uis.dto.course.CourseQueryDTO;
import com.example.uis.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface CourseMapper {


    CourseQueryDTO entityToCourseQueryDTO(Course course);

    Course courseCommandDtoToEntity(CourseCommandDTO courseCommandDTO);
}
