package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.service.ChapterService;
import com.techzen.techlearn.service.CourseService;
import com.techzen.techlearn.service.TeacherService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/courses")
public class CourseController {

    CourseService courseService;
    ChapterService chapterService;
    TeacherService teacherService;

    @GetMapping
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false, defaultValue = "1") int page,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(courseService.getAllCourse(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return JsonResponse.ok(courseService.getCourseById(id));
    }

    @GetMapping("/list-id")
    public ResponseEntity<?> getCourseByListId(@RequestParam List<Long> id) {
        return JsonResponse.ok(courseService.getCourseByListId(id));
    }

    @GetMapping("/{id}/chapters")
    public ResponseEntity<?> getChapterByIdCourse(@PathVariable Long id) {
        return JsonResponse.ok(chapterService.getChapterByIdCourse(id));
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<?> getTeacherByIdCourse(@PathVariable Long id) {
        return JsonResponse.ok(teacherService.getTeacherByIdCourse(id));
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody @Valid CourseRequestDTO request) {
        return JsonResponse.ok(courseService.addCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequestDTO request) {
        return JsonResponse.ok(courseService.updateCourse(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return JsonResponse.deleted();
    }
}
