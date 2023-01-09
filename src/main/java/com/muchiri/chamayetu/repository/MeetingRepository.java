package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Page<Meeting> findAll(Pageable pageable);
}
