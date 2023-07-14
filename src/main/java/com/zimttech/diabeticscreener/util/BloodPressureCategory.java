package com.zimttech.diabeticscreener.util;

public enum BloodPressureCategory {
    NORMAL("Blood pressure numbers of less than 120/80 mm Hg are considered within the normal range."),
    ELEVATED("Elevated blood pressure is when readings consistently range from 120-129 systolic and less than 80 mm Hg diastolic."),
    HYPERTENSION_STAGE_1("Hypertension Stage 1 is when blood pressure consistently ranges from 130 to 139 systolic or 80 to 89 mm Hg diastolic."),
    HYPERTENSION_STAGE_2("Hypertension Stage 2 is when blood pressure consistently is 140/90 mm Hg or higher."),
    HYPERTENSIVE_CRISIS("Hypertensive crisis requires immediate medical attention.");

    private final String description;

    BloodPressureCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
