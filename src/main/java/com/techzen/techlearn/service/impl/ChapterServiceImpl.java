package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.mapper.ChapterMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.service.ChapterService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterServiceImpl implements ChapterService {

    ChapterRepository chapterRepository;
    ChapterMapper chapterMapper;

    @Override
    public ChapterResponseDTO getChapterById(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
        return chapterMapper.toChapterResponseDTO(chapterEntity);
    }

    @Override
    public ChapterResponseDTO addChapter(ChapterRequestDTO request) {
        ChapterEntity chapterEntity = chapterMapper.toChapterEntity(request);
        ChapterEntity savedChapter = chapterRepository.save(chapterEntity);
        return chapterMapper.toChapterResponseDTO(savedChapter);
    }

    @Override
    public ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO request) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
        chapterMapper.updateChapterEntityFromDTO(request, chapterEntity);
        ChapterEntity updatedChapter = chapterRepository.save(chapterEntity);
        return chapterMapper.toChapterResponseDTO(updatedChapter);
    }

    @Override
    @Transactional
    public void deleteChapter(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
        chapterEntity.setIsDeleted(true); // Update the isDeleted flag instead of removing the entity
        chapterRepository.save(chapterEntity);
    }

    @Override
    public PageResponse<?> getAllChapters(int page, int pageSize) {
        Page<ChapterEntity> chapters = chapterRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, pageSize));
        List<ChapterResponseDTO> list = chapters.map(chapterMapper::toChapterResponseDTO).toList();

        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(chapters.getTotalPages())
                .items(list)
                .build();
    }
}
