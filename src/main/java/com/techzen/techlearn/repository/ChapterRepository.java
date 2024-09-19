package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.ChapterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    @Query("SELECT c FROM ChapterEntity c WHERE c.course.id IN :courseIds ORDER BY c.chapterOrder ASC")
    Page<ChapterEntity> findByCourseIdIn(@Param("courseIds") List<Long> courseIds, Pageable pageable);

}
