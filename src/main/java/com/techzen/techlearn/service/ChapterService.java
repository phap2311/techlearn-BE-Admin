package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;

public interface ChapterService {

    ChapterResponseDTO getChapterById(Long id);

    ChapterResponseDTO addChapter(ChapterRequestDTO request);

    ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO request);

    void deleteChapter(Long id);

    PageResponse<?> getAllChapters(int page, int pageSize);
}
