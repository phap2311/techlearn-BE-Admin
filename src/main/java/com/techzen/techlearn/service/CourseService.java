package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    PageResponse<?> getAllCourse(int page, int size);

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO addCourse(CourseRequestDTO request);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO request);

    void deleteCourse(Long id);

    List<CourseResponseDTO> getCourseByListId(List<Long> id);
}
