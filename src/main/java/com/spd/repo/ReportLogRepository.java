package com.spd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spd.model.ReportLog;

public interface ReportLogRepository extends JpaRepository<ReportLog, Long> {
}