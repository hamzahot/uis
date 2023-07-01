package com.example.uis.mappers;


import com.example.uis.dto.frequent_question.FrequentQuestionCommandDTO;
import com.example.uis.dto.frequent_question.FrequentQuestionQueryDTO;
import com.example.uis.entities.FrequentQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface FrequentQuestionMapper {

    FrequentQuestion commandDtoToEntity(FrequentQuestionCommandDTO frequentQuestionCommandDTO);

    FrequentQuestionQueryDTO entityToQueryDto(FrequentQuestion frequentQuestion);

}
