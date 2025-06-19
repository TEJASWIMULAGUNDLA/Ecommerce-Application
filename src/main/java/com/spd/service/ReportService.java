package com.spd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spd.model.ReportLog;
import com.spd.repo.ReportLogRepository;

import java.time.LocalDateTime;

@Service
public class ReportService {

    @Autowired
    private ReportLogRepository reportLogRepository;

    public void generateDailyReport() {
        try {
            // Example logic: you can generate Excel, PDF, etc.
            String filePath = "/reports/daily-report-" + System.currentTimeMillis() + ".txt";
            // Save file to disk logic (mocked here)
            System.out.println("Report saved to: " + filePath);

            ReportLog report = new ReportLog();
            report.setReportType("DAILY");
            report.setGeneratedAt(LocalDateTime.now());
            report.setFilePath(filePath);
            report.setSuccess(true);
            reportLogRepository.save(report);

        } catch (Exception e) {
            ReportLog report = new ReportLog();
            report.setReportType("DAILY");
            report.setGeneratedAt(LocalDateTime.now());
            report.setSuccess(false);
            report.setFilePath("N/A");
            reportLogRepository.save(report);
            System.err.println("Report generation failed: " + e.getMessage());
        }
    }
}
