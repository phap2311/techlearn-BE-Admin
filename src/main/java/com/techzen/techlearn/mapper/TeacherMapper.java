package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.TeacherRequestDTO;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import com.techzen.techlearn.dto.response.UserResponseDTO2;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherEntity toTeacherEntity(TeacherRequestDTO dto);

    TeacherResponseDTO toTeacherResponseDTO(TeacherEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "color", constant = "#FF5733")
    TeacherEntity toTeacherEntity(UserEntity user);
}