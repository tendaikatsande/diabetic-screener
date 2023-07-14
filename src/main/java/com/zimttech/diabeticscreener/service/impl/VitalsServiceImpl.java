package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.dto.VitalAggregateSummary;
import com.zimttech.diabeticscreener.entity.Vital;
import com.zimttech.diabeticscreener.repository.VitalsRepository;
import com.zimttech.diabeticscreener.service.VitalsService;
import com.zimttech.diabeticscreener.util.BloodPressureCategory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor

public class VitalsServiceImpl implements VitalsService {

    private final VitalsRepository repository;
    @Override
    public Page<Vital> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Vital> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Vital create(Vital vital) {
        return repository.save(vital);
    }

    @Override
    public Vital update(Long id, Vital vital) {
        return create(vital);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VitalAggregateSummary getVitalAggregateAsAt(Instant asAt) {
        List<Vital> vitals = repository.findByCreatedDate(asAt);
        long hs1 = vitals.stream().map(vital -> vital.getBloodPressure().equals(BloodPressureCategory.HYPERTENSION_STAGE_1)).toList().size();
        long hs2 = vitals.stream().map(vital -> vital.getBloodPressure().equals(BloodPressureCategory.HYPERTENSION_STAGE_2)).toList().size();
        long hCrisis = vitals.stream().map(vital -> vital.getBloodPressure().equals(BloodPressureCategory.HYPERTENSIVE_CRISIS)).toList().size();
        long normal = vitals.stream().map(vital -> vital.getBloodPressure().equals(BloodPressureCategory.NORMAL)).toList().size();
        long elevated = vitals.stream().map(vital -> vital.getBloodPressure().equals(BloodPressureCategory.ELEVATED)).toList().size();
        return VitalAggregateSummary.builder().hs1(hs1).hs2(hs2).normal(normal).elevated(elevated).hCrisis(hCrisis).grandTotal(vitals.size()).build();
    }
}
