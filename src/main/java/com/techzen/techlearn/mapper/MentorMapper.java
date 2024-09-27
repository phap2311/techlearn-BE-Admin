package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.MentorRequestDTO;
import com.techzen.techlearn.dto.response.MentorResponseDTO;
import com.techzen.techlearn.entity.MentorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MentorMapper {
    MentorEntity toMentorEntity(MentorRequestDTO DTO);

    MentorResponseDTO toMentorResponseDto(MentorEntity entity);
}
