package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.MentorRequestDTO;
import com.techzen.techlearn.dto.response.MentorResponseDTO;
import com.techzen.techlearn.entity.MentorEntity;
import com.techzen.techlearn.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MentorMapper {
    MentorEntity toMentorEntity(MentorRequestDTO DTO);

    MentorResponseDTO toMentorResponseDto(MentorEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "color", constant = "#CCCCFF")
    MentorEntity toMentorEntity(UserEntity user);
}
