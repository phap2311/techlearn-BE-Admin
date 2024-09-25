package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.LessonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    Page<LessonEntity> findAllByChapterId(Long idChapter, Pageable pageable);

    @Query("SELECT MAX(c.lessonOrder) FROM LessonEntity c WHERE c.chapter.id = :chapterId")
    Integer findMaxOrderByChapterId(@Param("chapterId") Long chapterId);
//    Integer findMaxLessonOrderByChapterId(Long chapterId);

}

