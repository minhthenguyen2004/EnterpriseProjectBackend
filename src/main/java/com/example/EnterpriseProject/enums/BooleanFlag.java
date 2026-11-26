package com.example.EnterpriseProject.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Cờ trạng thái nhị phân chuẩn hóa cho các cấu hình và kiểm tra trạm
 */
public enum BooleanFlag {
    TRUE,
    FALSE;

    @JsonCreator
    public static BooleanFlag fromString(String value) {
        if (value == null) {
            return FALSE;
        }
        String clean = value.trim().toUpperCase();
        if (clean.equals("TRUE") || clean.equals("1") || clean.equals("YES") || clean.equals("Y") || clean.equals("ACTIVE")) {
            return TRUE;
        }
        return FALSE;
    }

    public boolean toBoolean() {
        return this == TRUE;
    }
}

