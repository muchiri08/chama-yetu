package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
