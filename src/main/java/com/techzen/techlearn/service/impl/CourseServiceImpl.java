package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.entity.TechStackEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.CourseMapper;
import com.techzen.techlearn.mapper.TeacherMapper;
import com.techzen.techlearn.repository.CourseRepository;
import com.techzen.techlearn.repository.TeacherRepository;
import com.techzen.techlearn.repository.TechStackRepository;
import com.techzen.techlearn.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;
    TechStackRepository techStackRepository;
    TeacherRepository teacherRepository;

    @Override
    public PageResponse<?> getAllCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Page<CourseEntity> course = courseRepository.findAll(pageable);
        List<CourseResponseDTO> list = course.map(courseMapper::toCourseResponseDTO)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(size)
                .totalPage(course.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        return courseMapper.toCourseResponseDTO(course);
    }

    @Override
    public CourseResponseDTO addCourse(CourseRequestDTO request) {
        var course = courseMapper.toCourseEntity(request);
        course.setIsDeleted(false);
        List<TeacherEntity> teachers = request.getTeacher().stream()
                .map(teacherDto -> teacherRepository.findById(teacherDto.getId()).orElseThrow(() ->
                        new ApiException(ErrorCode.TEACHER_NOT_EXISTED)))
                .collect(Collectors.toList());
        course.setTeachers(teachers);
        course.setTechStackEntities(getTechStackEntities(request, course));
        return courseMapper.toCourseResponseDTO(courseRepository.save(course));
    }


    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        var existingCourse  = courseRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        existingCourse.getTechStackEntities()
                .forEach(tech -> tech.getCourses().remove(existingCourse));
        existingCourse.getTechStackEntities().clear();
        courseRepository.flush();
        List<TeacherEntity> teachers = request.getTeacher().stream()
                .map(teacherDto -> teacherRepository.findById(teacherDto.getId()).orElseThrow(() ->
                        new ApiException(ErrorCode.TEACHER_NOT_EXISTED)))
                .collect(Collectors.toList());
        var courseMap = courseMapper.toCourseEntity(request);
        courseMap.setId(id);
        courseMap.setTechStackEntities(getTechStackEntities(request, courseMap));
        courseMap.setIsDeleted(false);
        courseMap.setTeachers(teachers);
        return courseMapper.toCourseResponseDTO(courseRepository.save(courseMap));
    }

    @Override
    public void deleteCourse(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        course.setIsDeleted(true);
        courseRepository.save(course);
    }

    @Override
    public List<CourseResponseDTO> getCourseByListId(List<Long> id) {
        var courses = courseRepository.findAllById(id);
        if (courses.isEmpty()) {
            throw new ApiException(ErrorCode.COURSE_NOT_EXISTED);
        }
        return courses.stream().map(courseMapper::toCourseResponseDTO)
                .collect(Collectors.toList());
    }

    private List<TechStackEntity> getTechStackEntities(CourseRequestDTO requestDTO, CourseEntity course) {
        return requestDTO.getTechStack().stream()
                .map(id -> techStackRepository.findById(id)
                        .orElseThrow(() -> new ApiException(ErrorCode.TECHSTACK_NOT_EXISTED)))
                .peek(techStack -> techStack.getCourses().add(course))
                .collect(Collectors.toList());
    }
}
