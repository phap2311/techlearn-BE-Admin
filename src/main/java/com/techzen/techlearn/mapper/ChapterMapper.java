package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.MentorResponseDTO;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.entity.MentorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(source = "courseId", target = "course.id")
    ChapterEntity toChapterEntity(ChapterRequestDTO chapterRequestDTO);

    @Mapping(source = "course.id", target = "courseId")
    ChapterResponseDTO toChapterResponseDTO(ChapterEntity chapterEntity);

/*    @Mapping(source = "courseId", target = "course.id")
    void updateChapterEntityFromDTO(ChapterRequestDTO chapterRequestDTO, @MappingTarget ChapterEntity chapterEntity);*/

    @Mapping(target = "mentor", source = "mentors", qualifiedByName = "mapMentorEntitiesToResponseDTOs")
    ChapterResponseDTO toChapterMentorResponseDTO(ChapterEntity chapterEntity);
    @Named("mapMentorEntitiesToResponseDTOs")
    default List<MentorResponseDTO> mapMentorEntitiesToResponseDTOs(List<MentorEntity> mentors) {
        return mentors.stream()
                .map(mentor -> MentorResponseDTO.builder()
                        .id(mentor.getId())
                        .name(mentor.getName())
                        .avatar(mentor.getAvatar())
                        .color(mentor.getColor())
                        .email(mentor.getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
