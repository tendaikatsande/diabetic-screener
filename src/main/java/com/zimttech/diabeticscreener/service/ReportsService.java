package com.zimttech.diabeticscreener.service;

import com.zimttech.diabeticscreener.dto.DashboardStatsResponse;

import java.time.Instant;
import java.util.Optional;

public interface ReportsService {
    DashboardStatsResponse getDashboardStatsResponse(Instant asAt);
}
