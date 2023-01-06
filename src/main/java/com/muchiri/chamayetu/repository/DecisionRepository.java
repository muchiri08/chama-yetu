package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Decision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionRepository extends JpaRepository<Decision,  Long> {
    Page<Decision> findAll(Pageable pageable);
}
