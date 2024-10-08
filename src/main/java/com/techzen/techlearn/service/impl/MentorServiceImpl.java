package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.MentorChapterRequestDTO;
import com.techzen.techlearn.dto.request.MentorRequestDTO;
import com.techzen.techlearn.dto.response.MentorResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.entity.MentorEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.MentorMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.repository.MentorRepository;
import com.techzen.techlearn.service.MentorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorServiceImpl implements MentorService {
    MentorRepository mentorRepository;
    MentorMapper mentorMapper;
    ChapterRepository chapterRepository;

    @Override
    public PageResponse<?> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<MentorEntity> mentors = mentorRepository.findAll(pageable);
        List<MentorResponseDTO> list = mentors.map(mentorMapper::toMentorResponseDto).stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(mentors.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public List<MentorResponseDTO> findAllMentors() {
        List<MentorEntity> mentors = mentorRepository.findAll();
        if (mentors.isEmpty()) {
            throw new ApiException(ErrorCode.MENTOR_NOT_EXISTED);
        }
        return mentors.stream()
                .map(mentorMapper::toMentorResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MentorResponseDTO getMentorById(UUID id) {
        MentorEntity mentorEntity = mentorRepository.findMentorById(id).orElseThrow(
                () -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED)
        );
        return mentorMapper.toMentorResponseDto(mentorEntity);
    }


    @Override
    public MentorResponseDTO addMentor(MentorRequestDTO request) {
        MentorEntity mentorEntity = mentorMapper.toMentorEntity(request);
        mentorEntity.setIsDeleted(false);
        if (mentorEntity.getId() == null) {
            mentorEntity.setId(UUID.randomUUID());
        }
        mentorEntity = mentorRepository.save(mentorEntity);
        return mentorMapper.toMentorResponseDto(mentorEntity);
    }

    @Override
    public MentorResponseDTO updateMentor(UUID id, MentorRequestDTO request) {
        mentorRepository.findMentorById(id).orElseThrow(
                () -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED)
        );
        var mentor = mentorMapper.toMentorEntity(request);
        mentor.setName(request.getName());
        mentor.setColor(request.getColor());
        mentor.setAvatar(request.getAvatar());
        mentor.setEmail(request.getEmail());
        return mentorMapper.toMentorResponseDto(mentor);
    }

    @Override
    public void deleteMentor(UUID id) {
        var mentor = mentorRepository.findMentorById(id).orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));
        mentor.setIsDeleted(true);
        mentorRepository.save(mentor);
    }

    @Override
    public MentorResponseDTO addMentorToChapter(UUID uuid, Long id) {
        MentorEntity mentor = mentorRepository.findById(uuid)
                .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));

        ChapterEntity chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        mentor.getChapterEntities().add(chapter);
        MentorEntity updatedMentor = mentorRepository.save(mentor);
        return mentorMapper.toMentorResponseDto(updatedMentor);
    }

    @Override
    public List<MentorResponseDTO> addMentorToAllChapter(UUID mentorId, List<Long> chapterIds) {
        MentorEntity mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));
        List<ChapterEntity> chapters = chapterRepository.findAllById(chapterIds);
        if (chapters.size() != chapterIds.size()) {
            throw new ApiException(ErrorCode.CHAPTER_NOT_EXISTED);
        }
        for (ChapterEntity chapter : chapters) {
            mentor.getChapterEntities().add(chapter);
        }
        MentorEntity updatedMentor = mentorRepository.save(mentor);
        return updatedMentor.getChapterEntities().stream()
                .map(chapter -> mentorMapper.toMentorResponseDto(updatedMentor))
                .collect(Collectors.toList());
    }

    @Override
    public MentorResponseDTO updateMentorToChapter(UUID uuid, Long oldChapterId, MentorChapterRequestDTO request) {
        mentorRepository.findById(uuid)
                .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));
        chapterRepository.findById(oldChapterId)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        Long newChapterId = Long.valueOf(request.getChapterId());
        UUID newMentorId = UUID.fromString(request.getMentorId());
        mentorRepository.updateMentorChapter(oldChapterId, newChapterId, uuid, newMentorId);
        MentorEntity updatedMentor = mentorRepository.findById(uuid)
                .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));
        return mentorMapper.toMentorResponseDto(updatedMentor);
    }


    @Override
    public void deleteMentorToChapter(UUID uuid, Long id) {
        mentorRepository.findById(uuid)
                .orElseThrow(() -> new ApiException(ErrorCode.MENTOR_NOT_EXISTED));
        chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        mentorRepository.deleteMentorChapter(uuid, id);
    }

}
