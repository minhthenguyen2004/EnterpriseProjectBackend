package com.example.EnterpriseProject.security.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for user login")
public class LoginRequest {

    @Schema(description = "User Employee / Station Card ID", example = "2026-FII")
    @NotBlank(message = "Please enter Cart Id")
    private String cardId;

    @Schema(description = "User account password", example = "12345678")
    @NotBlank(message = "Please enter your password")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;
}
