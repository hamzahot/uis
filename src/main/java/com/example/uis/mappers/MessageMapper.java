package com.example.uis.mappers;


import com.example.uis.dto.message.MessageCommandDTO;
import com.example.uis.dto.message.MessageQueryDTO;
import com.example.uis.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface MessageMapper {


    Message commandDtoToEntity(MessageCommandDTO messageCommandDTO);

    @Mapping(source = "student.fullName", target = "studentName")
    MessageQueryDTO entityToQueryDto(Message message);

}
