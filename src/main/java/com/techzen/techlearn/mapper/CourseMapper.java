package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.dto.response.TechStackResponseDTO;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.entity.TechStackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "techStack", source = "techStackEntities", qualifiedByName = "mapTechStackEntitiesToDTOs")
    CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseRequestDTO courseRequestDTO);

    @Named("mapTechStackEntitiesToDTOs")
    default List<TechStackResponseDTO> mapTechStackEntitiesToDTOs(List<TechStackEntity> techStackEntities) {
        return techStackEntities.stream()
                .map(entity -> TechStackResponseDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
