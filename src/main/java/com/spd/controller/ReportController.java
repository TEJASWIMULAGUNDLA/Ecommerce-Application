package com.spd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spd.model.ReportLog;
import com.spd.repo.ReportLogRepository;

@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    @Autowired
    private ReportLogRepository reportLogRepository;

    @GetMapping
    public List<ReportLog> getAllReports() {
        return reportLogRepository.findAll();
    }
}