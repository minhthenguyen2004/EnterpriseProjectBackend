package com.example.EnterpriseProject.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO nhận ảnh tổng quan từ mảng imagesummarys trong aoidata
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiSummaryImageDto {

    private String camera;

    private String imagedata;

    private String description;
}
