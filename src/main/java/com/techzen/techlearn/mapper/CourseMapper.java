package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.entity.TechStackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "techStack", source = "techStackEntities", qualifiedByName = "mapTechStackEntitiesToNames")
    CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseRequestDTO courseRequestDTO);

    @Named("mapTechStackEntitiesToNames")
    default List<String> mapTechStackEntitiesToNames(List<TechStackEntity> techStackEntities) {
        return techStackEntities.stream()
                .map(TechStackEntity::getName)
                .collect(Collectors.toList());
    }
}
