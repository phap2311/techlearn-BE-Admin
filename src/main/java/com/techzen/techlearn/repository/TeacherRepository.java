package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {
    @Query("select t from TeacherEntity t where t.id =:id")
    Optional<TeacherEntity> findTeacherById(UUID id);
}
