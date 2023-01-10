package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Page<Meeting> findAll(Pageable pageable);

    @Query("SELECT m FROM Meeting m WHERE m.date = :date")
    Optional<Meeting> findMeetingByDate(@Param("date") LocalDate date);
}
