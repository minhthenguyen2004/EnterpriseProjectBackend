package com.example.EnterpriseProject.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Kết quả kiểm tra hoặc thực thi tại trạm
 */
public enum TestResult {
    PASS,
    FAIL,
    NA;

    @JsonCreator
    public static TestResult fromString(String value) {
        if (value == null) {
            return NA;
        }
        String clean = value.trim().toUpperCase().replace("/", "").replace("-", "");
        if (clean.equals("NA") || clean.equals("N A")) {
            return NA;
        }
        try {
            return TestResult.valueOf(clean);
        } catch (IllegalArgumentException e) {
            return NA;
        }
    }
}

