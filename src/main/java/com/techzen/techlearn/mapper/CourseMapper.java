package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity);
    CourseEntity toCourseEntity(CourseRequestDTO courseRequestDTO);
}
