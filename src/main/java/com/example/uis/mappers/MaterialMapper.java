package com.example.uis.mappers;


import com.example.uis.dto.material.MaterialCommandDTO;
import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.entities.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface MaterialMapper {

    Material commandDtoToEntity(MaterialCommandDTO materialCommandDTO);

    MaterialQueryDTO entityToQueryDto(Material material);

}
