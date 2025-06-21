package com.ashokit.GenerateReportTime;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ashokit.service.SaleReportService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor          
@EnableScheduling                 
public class GenerateReport {

    private final SaleReportService service;
    private static final Logger log = (Logger) LoggerFactory.getLogger(GenerateReport.class);

    
    @Scheduled(cron = "0 0 6 * * *", zone = "Asia/Kolkata")
    public void generateDailyReport() {
        LocalDate today      = LocalDate.now();
        LocalDate yesterday  = today.minusDays(1);

        log.info("▶ Generating daily report for {}", yesterday);
        service.generateReport(yesterday, yesterday, null, null);
        log.info("✅ Daily report completed");
    }

   
    @Scheduled(cron = "0 0 9 * * MON", zone = "Asia/Kolkata") // drop “?” for max compatibility
    public void generateWeeklyReport() {
        LocalDate today      = LocalDate.now();
        LocalDate startDate  = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate endDate    = today.minusWeeks(1).with(DayOfWeek.SUNDAY);

        log.info("▶ Generating weekly report for {} to {}", startDate, endDate);
        service.generateReport(startDate, endDate, null, null);
        log.info("✅ Weekly report completed");
    }
    
    /* This is Every Month Report on 1st from the month */
    @Scheduled(cron = "0 0 6 1 * *", zone = "Asia/Kolkata") // Every 1st day of the month at 6 AM
    public void generateMonthlyReport() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusMonths(1).withDayOfMonth(1);      // 1st of previous month
        LocalDate endDate = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth()); // Last day of previous month

        System.out.println("Generating monthly report for: " + startDate + " to " + endDate);

        service.generateReport(startDate, endDate, null, null);

        System.out.println("Monthly report generation completed.");
    }

}
