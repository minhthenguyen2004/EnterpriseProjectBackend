package com.example.EnterpriseProject.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO nhận thông tin kiểm tra từng linh kiện con trong mảng components
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiComponentDto {

    private String componentname;

    private String location;

    private BigDecimal score;

    private BigDecimal measure;

    private String result;

    private String errorcode;

    private String cropimage;
}
