package com.example.EnterpriseProject.payload.dto;

import com.example.EnterpriseProject.enums.AoiCamera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO dữ liệu kiểm tra FOV AOI
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AoiFovInspectionDto {

    private Integer fovIndex;

    private AoiCamera camera;

    private String fovImagePath;

    private Instant createdAt;

    @Builder.Default
    private List<AoiComponentDto> components = new ArrayList<>();
}
