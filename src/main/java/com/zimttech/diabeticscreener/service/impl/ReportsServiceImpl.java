package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.dto.DashboardStatsResponse;
import com.zimttech.diabeticscreener.dto.VitalAggregateSummary;
import com.zimttech.diabeticscreener.service.ReportsService;
import com.zimttech.diabeticscreener.service.VitalsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {

    private final VitalsService vitalsService;

    @Override
    public DashboardStatsResponse getDashboardStatsResponse(Instant asAt) {
        VitalAggregateSummary vitalAggregateSummary =  vitalsService.getVitalAggregateAsAt(asAt);
        return  new DashboardStatsResponse(vitalAggregateSummary);
    }
}
