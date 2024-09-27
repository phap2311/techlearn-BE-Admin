package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.MentorChapterRequestDTO;
import com.techzen.techlearn.dto.request.MentorRequestDTO;
import com.techzen.techlearn.dto.response.MentorResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MentorService {
    PageResponse<?> findAll(int page, int pageSize);

    MentorResponseDTO getMentorById(UUID id);

    MentorResponseDTO addMentor(MentorRequestDTO request);

    MentorResponseDTO updateMentor(UUID id, MentorRequestDTO request);

    void deleteMentor(UUID id);

    MentorResponseDTO addMentorToChapter(UUID uuid, Long id);

    MentorResponseDTO updateMentorToChapter(UUID uuid, Long id, MentorChapterRequestDTO request);

    void deleteMentorToChapter(UUID uuid, Long id);

    List<MentorResponseDTO> getAll();
}
