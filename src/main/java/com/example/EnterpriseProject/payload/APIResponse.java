package com.example.EnterpriseProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard API response wrapper")
public class APIResponse {
    @Schema(description = "Success status flag", example = "true")
    private boolean status;

    @Schema(description = "Descriptive response message", example = "Operation completed successfully")
    private String message;
}