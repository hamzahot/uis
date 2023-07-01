package com.example.uis.mappers;


import com.example.uis.dto.student.StudentQueryDTO;
import com.example.uis.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface StudentMapper {

    StudentQueryDTO entityToQueryDTO(Student student);
}
