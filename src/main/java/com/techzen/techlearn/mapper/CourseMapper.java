package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import com.techzen.techlearn.dto.response.TechStackResponseDTO;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.entity.TechStackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "techStack", source = "techStackEntities", qualifiedByName = "mapTechStackEntitiesToDTOs")
    @Mapping(target = "teacher", source = "teachers", qualifiedByName = "mapTeacherEntitiesToResponseDTOs")
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

    @Named("mapTeacherEntitiesToResponseDTOs")
    default List<TeacherResponseDTO> mapTeacherEntitiesToResponseDTOs(List<TeacherEntity> teachers) {
        return teachers.stream()
                .map(teacher -> TeacherResponseDTO.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .avatar(teacher.getAvatar())
                        .color(teacher.getColor()   )
                        .build())
                .collect(Collectors.toList());
    }
}
