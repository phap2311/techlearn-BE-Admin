package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.response.TechStackResponseDTO;
import com.techzen.techlearn.entity.TechStackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechStackMapper {

    TechStackResponseDTO toTechStackResponse(TechStackEntity techStackEntity);
}
