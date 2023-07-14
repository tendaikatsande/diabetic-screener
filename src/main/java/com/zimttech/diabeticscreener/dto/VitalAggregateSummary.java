package com.zimttech.diabeticscreener.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitalAggregateSummary {

    private long normal;
    private long elevated;
    private long hs1;
    private long hs2;
    private long hCrisis;
    private long grandTotal;
    private long screened;
    private long pending;
    private long patients;


}
