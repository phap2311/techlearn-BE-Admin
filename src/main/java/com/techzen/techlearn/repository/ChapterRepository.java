package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.ChapterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    Page<ChapterEntity> findAllByCourseId(Long id, Pageable pageable);

    @Query("SELECT MAX(c.chapterOrder) FROM ChapterEntity c WHERE c.course.id = :courseId")
    Integer findMaxOrderByCourseId(@Param("courseId") Long courseId);
}
