package com.techzen.techlearn.repository;

import com.techzen.techlearn.entity.TechStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStackRepository extends JpaRepository<TechStackEntity, Long> {
}
