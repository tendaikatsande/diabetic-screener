package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.dto.DashboardStatsResponse;
import com.zimttech.diabeticscreener.dto.VitalAggregateSummary;
import com.zimttech.diabeticscreener.service.ReportsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports")
@CrossOrigin()
public class ReportController {


    @Autowired
    ReportsService reportsService;
    @GetMapping("/dashboard/{asAt}")
    public ResponseEntity<DashboardStatsResponse> getVitalAggregateSummary(@PathVariable String asAt){

        return  ResponseEntity.ok(reportsService.getDashboardStatsResponse(Instant.parse(asAt)));
    }

}
