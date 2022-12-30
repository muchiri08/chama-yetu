package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.FinancialReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialReportDataRepository extends JpaRepository<FinancialReportData, Long> {
}
