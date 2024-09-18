package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.entity.ChapterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    ChapterEntity toChapterEntity(ChapterRequestDTO chapterRequestDTO);

    ChapterResponseDTO toChapterResponseDTO(ChapterEntity chapterEntity);

    void updateChapterEntityFromDTO(ChapterRequestDTO chapterRequestDTO, @MappingTarget ChapterEntity chapterEntity);
}
