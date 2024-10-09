package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {
    @Query("select t from TeacherEntity t where t.id =:id")
    Optional<TeacherEntity> findTeacherById(UUID id);

    Optional<List<TeacherEntity>> findTeacherByCoursesId(Long id);

    TeacherEntity findByUser(UserEntity existingUser);

    @Query("select m from TeacherEntity m where m.id = :id")
    TeacherEntity findTeacherEntityById(UUID id);
}
