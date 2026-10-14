package com.example.EnterpriseProject.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO nhận từng vùng chụp FOV trong mảng inspectionimages của aoidata
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiInspectionImageDto {

    private Integer fov;

    private String camera;

    private String image;

    @Builder.Default
    private List<AoiComponentDto> components = new ArrayList<>();
}
