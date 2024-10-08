package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    @Query("SELECT c FROM CourseEntity c WHERE c.isActive = true ORDER BY c.id ASC")
    Page<CourseEntity> findAllActiveCourses(Pageable pageable);
}
