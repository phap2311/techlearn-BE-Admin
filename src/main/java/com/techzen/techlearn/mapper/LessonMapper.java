package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.LessonRequestDTO;
import com.techzen.techlearn.dto.response.LessonResponseDTO;
import com.techzen.techlearn.entity.LessonEntity;
import com.techzen.techlearn.repository.ChapterRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ChapterRepository.class)
public interface LessonMapper {
    @Mapping(source = "chapter.id", target = "chapter")
    LessonResponseDTO toLessonResponseDTO(LessonEntity lesson);

    LessonEntity toLessonEntity(LessonRequestDTO lesson);
}
