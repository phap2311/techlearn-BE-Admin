package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.entity.MentorEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.ChapterMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.repository.CourseRepository;
import com.techzen.techlearn.repository.MentorRepository;
import com.techzen.techlearn.service.ChapterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterServiceImpl implements ChapterService {

    ChapterRepository chapterRepository;
    ChapterMapper chapterMapper;
    CourseRepository courseRepository;
    MentorRepository mentorRepository;

    @Override
    public ChapterResponseDTO getChapterById(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        return chapterMapper.toChapterResponseDTO(chapterEntity);
    }

    @Override
    public ChapterResponseDTO addChapter(ChapterRequestDTO request) {
        courseRepository.findById(Long.parseLong(request.getCourseId()))
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        var chapterEntity = chapterMapper.toChapterEntity(request);
        var chapter_order = chapterRepository.findMaxOrderByCourseId(Long.parseLong(request.getCourseId()));
        chapterEntity.setChapterOrder(chapter_order != null? chapter_order + 1 : 1);
        chapterEntity.setIsDeleted(false);
        List<MentorEntity> mentors = request.getMentor().stream()
                .map(mentorDto -> mentorRepository.findById(mentorDto.getId())
                        .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED)))
                .collect(Collectors.toList());
        chapterEntity.setMentors(mentors);
        return chapterMapper.toChapterResponseDTO(chapterRepository.save(chapterEntity));
    }

    @Override
    public ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO request) {
        chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        courseRepository.findById(Long.parseLong(request.getCourseId()))
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        List<MentorEntity> mentors = request.getMentor().stream()
                .map(mentorDto -> mentorRepository.findById(mentorDto.getId())
                        .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED)))
                .collect(Collectors.toList());
        var chapterEntity = chapterMapper.toChapterEntity(request);
        chapterEntity.setId(id);
        chapterEntity.setIsDeleted(false);
        chapterEntity.setMentors(mentors);
        return chapterMapper.toChapterResponseDTO(chapterRepository.save(chapterEntity));
    }

    @Override
    public void deleteChapter(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        chapterEntity.setIsDeleted(true);
        chapterRepository.save(chapterEntity);
    }

    @Override
    public PageResponse<?> getAllChapters(int page, int pageSize, Long id) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize,
                Sort.by("chapterOrder"));
        Page<ChapterEntity> chapter = chapterRepository.findAllByCourseId(id, pageable);
        List<ChapterResponseDTO> list = chapter.map(chapterMapper::toChapterResponseDTO)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(chapter.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public void updateOrder(List<OrderDTO> orderDTOS) {
        List<ChapterEntity> lessonsToUpdate = orderDTOS.stream()
                .map(dto -> chapterRepository.findById(Long.parseLong(dto.getId()))
                        .map(lesson -> {
                            lesson.setChapterOrder(Integer.parseInt(dto.getOrder()));
                            return lesson;
                        })
                        .orElseThrow(() -> new ApiException(ErrorCode.LESSON_NOT_EXISTED)))
                .collect(Collectors.toList());
        chapterRepository.saveAll(lessonsToUpdate);
    }

    @Override
    public List<ChapterResponseDTO> getChapterByIdCourse(Long id) {
        var chapters = chapterRepository.findAllByCourseId(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        return chapters.stream().map(chapterMapper::toChapterResponseDTO)
                .collect(Collectors.toList());
    }
}
