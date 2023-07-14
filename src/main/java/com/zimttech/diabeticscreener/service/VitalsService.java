package com.zimttech.diabeticscreener.service;

import com.zimttech.diabeticscreener.dto.VitalAggregateSummary;
import com.zimttech.diabeticscreener.entity.Vital;

import java.time.Instant;

public interface VitalsService extends CrudContract<Vital> {
    VitalAggregateSummary getVitalAggregateAsAt(Instant asAt);
}
