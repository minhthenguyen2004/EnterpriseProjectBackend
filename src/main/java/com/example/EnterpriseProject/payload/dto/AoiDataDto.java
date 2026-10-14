package com.example.EnterpriseProject.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO nhận dữ liệu kiểm tra ngoại quan quang học từ khối aoidata
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiDataDto {

    private BigDecimal cycletime;

    private String carriercode;

    @Builder.Default
    private List<AoiSummaryImageDto> imagesummarys = new ArrayList<>();

    @Builder.Default
    private List<AoiInspectionImageDto> inspectionimages = new ArrayList<>();
}
