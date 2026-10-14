package com.example.EnterpriseProject.payload.output;

import com.example.EnterpriseProject.payload.dto.KpsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Standard API response wrapper")
public class APIResponse {

    @Schema(description = "Success status code (0: success, 22/other: error code)", example = "0")
    private Integer status; // 0 or 22

    @Schema(description = "Descriptive response message", example = "Operation completed successfully")
    private String message;

    private List<KpsDto> kps;

    public APIResponse(boolean isSuccess, String message) {
        this.status = isSuccess ? 0 : 1;
        this.message = message;
    }

    public APIResponse(boolean isSuccess, String message, List<KpsDto> kps) {
        this.status = isSuccess ? 0 : 1;
        this.message = message;
        this.kps = kps;
    }

    public APIResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}