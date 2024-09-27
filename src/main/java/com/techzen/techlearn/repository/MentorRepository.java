package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, UUID> {

    @Query("select m from MentorEntity m where m.id = :id")
    Optional<MentorEntity> findMentorById(UUID id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_chapter_mentor WHERE mentor_id = :uuid AND chapter_id = :id", nativeQuery = true)
    void deleteMentorChapter(@Param("uuid") UUID uuid, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tbl_chapter_mentor SET chapter_id = :newChapterId, mentor_id = :newMentorId " +
            "WHERE mentor_id = :uuid AND chapter_id = :oldChapterId", nativeQuery = true)
    void updateMentorChapter(@Param("oldChapterId") Long oldChapterId,
                             @Param("newChapterId") Long newChapterId,
                             @Param("uuid") UUID uuid,
                             @Param("newMentorId") UUID newMentorId);

}
