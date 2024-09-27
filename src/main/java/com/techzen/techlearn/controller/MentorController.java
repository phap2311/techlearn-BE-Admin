package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.MentorChapterRequestDTO;
import com.techzen.techlearn.dto.request.MentorRequestDTO;
import com.techzen.techlearn.service.MentorService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/mentors")
public class MentorController {
    MentorService mentorService;

    @GetMapping
    public ResponseEntity<?> getAllMentor(@RequestParam(required = false, defaultValue = "1") int page,
                                           @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(mentorService.findAll(page, pageSize));
    }

//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        return JsonResponse.ok(mentorService.getAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMentorById(@PathVariable UUID id) {
        return JsonResponse.ok(mentorService.getMentorById(id));
    }

    @PostMapping
    public ResponseEntity<?> addMentor(@RequestBody @Valid MentorRequestDTO request) {
        return JsonResponse.created(mentorService.addMentor(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable UUID id, @RequestBody @Valid MentorRequestDTO request) {
        return JsonResponse.ok(mentorService.updateMentor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable UUID id) {
        mentorService.deleteMentor(id);
        return JsonResponse.deleted();
    }

    @PostMapping("{mentor_id}/create-mentor-chapter/{chapter_id}")
    public ResponseEntity<?> addMentorToChapter(@PathVariable UUID mentor_id, @PathVariable Long chapter_id) {
        return ResponseEntity.ok(mentorService.addMentorToChapter(mentor_id, chapter_id));
    }

    @PutMapping("/{mentor_id}/update-mentor-chapter/{chapter_id}")
    public ResponseEntity<?> updateMentorToChapter(@PathVariable UUID mentor_id, @PathVariable Long chapter_id, @RequestBody @Valid MentorChapterRequestDTO request) {
        return ResponseEntity.ok(mentorService.updateMentorToChapter(mentor_id, chapter_id, request));
    }

    @DeleteMapping("/{uuid}/delete-mentor-chapter/{id}")
    public ResponseEntity<?> deleteMentorToChapter(@PathVariable UUID uuid, @PathVariable Long id) {
        mentorService.deleteMentorToChapter(uuid, id);
        return JsonResponse.deleted();
    }

}
