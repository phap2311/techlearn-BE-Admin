package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.ChapterMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.repository.CourseRepository;
import com.techzen.techlearn.service.ChapterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterServiceImpl implements ChapterService {

    ChapterRepository chapterRepository;
    ChapterMapper chapterMapper;
    CourseRepository courseRepository;

    @Override
    public ChapterResponseDTO getChapterById(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        return chapterMapper.toChapterResponseDTO(chapterEntity);
    }

    @Override
    public ChapterResponseDTO addChapter(ChapterRequestDTO request) {
        var chapterEntity = chapterMapper.toChapterEntity(request);

        Optional<CourseEntity> courseOpt = courseRepository.findById(request.getCourseId());

        if (courseOpt.isEmpty()) {
            throw new ApiException(ErrorCode.COURSE_NOT_EXISTED);
        }

        CourseEntity curCourse = courseOpt.get();
        chapterEntity.setCourse(curCourse);
        chapterEntity.setIsDeleted(false);
        ChapterEntity savedChapter = chapterRepository.save(chapterEntity);
        return chapterMapper.toChapterResponseDTO(savedChapter);
    }

    @Override
    public ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO request) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        chapterMapper.updateChapterEntityFromDTO(request, chapterEntity);
        ChapterEntity updatedChapter = chapterRepository.save(chapterEntity);
        return chapterMapper.toChapterResponseDTO(updatedChapter);
    }

    @Override
    public void deleteChapter(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        chapterEntity.setIsDeleted(true);
        chapterRepository.save(chapterEntity);
    }

    @Override
    public PageResponse<?> getAllChapters(int page, int pageSize) {
        Page<ChapterEntity> chapters = chapterRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, pageSize));
        List<ChapterResponseDTO> listChapter = chapters.map(chapterMapper::toChapterResponseDTO).toList();

        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(chapters.getTotalPages())
                .items(listChapter)
                .build();
    }
}
