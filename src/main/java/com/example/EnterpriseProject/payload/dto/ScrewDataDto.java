package com.example.EnterpriseProject.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO nhận dữ liệu siết ốc trong mảng screwdata
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrewDataDto {

    private String position;

    private String action;

    private BigDecimal standardvolume;

    private BigDecimal limitvolume;

    private BigDecimal standardheight;

    private BigDecimal limitheight;

    private BigDecimal realvolume;

    private BigDecimal realheight;

    private BigDecimal torque;

    private BigDecimal angle;

    private BigDecimal time;

    private String result;

    private String errorcode;
}
