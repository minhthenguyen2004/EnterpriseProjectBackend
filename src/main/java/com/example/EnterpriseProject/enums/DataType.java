package com.example.EnterpriseProject.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Loại hành động API từ trạm tự động hóa
 */
public enum DataType {
    AUTOLINK,
    PRECHECK,
    UNDO,
    RESET,
    STATUS;

    @JsonCreator
    public static DataType fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return DataType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

