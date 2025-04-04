package com.example.system1221.controller;

import com.example.system1221.dto.DailyReportDTO;
import com.example.system1221.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}/{date}")
    public DailyReportDTO getDailyReport(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.getDailyReport(userId, date);
    }

    @GetMapping("/history/{userId}")
    public List<DailyReportDTO> getHistory(@PathVariable Long userId) {
        return reportService.getMealHistory(userId);
    }

    @GetMapping("/check/{userId}/{date}")
    public boolean isWithinCalorieLimit(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.isWithinCalorieLimit(userId, date);
    }
}
