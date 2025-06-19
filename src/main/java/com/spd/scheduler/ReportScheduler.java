package com.spd.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spd.service.ReportService;

@Component
public class ReportScheduler {

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0 0 * * ?") // Midnight every day
    public void runDailyReport() {
        reportService.generateDailyReport();
    }
}
