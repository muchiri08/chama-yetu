package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Contribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    Page<Contribution> findAll(Pageable pageable);
    @Query("SELECT c FROM Contribution c WHERE c.dateTime BETWEEN :fromDate AND :toDate")
    Page<Contribution> findContributionByDateTimeBetween(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, Pageable pageable);

    @Query("SELECT c FROM Contribution c WHERE c.member.id = :id")
    Page<Contribution> findByMemberId(@Param("id") Long id, Pageable pageable);
}
