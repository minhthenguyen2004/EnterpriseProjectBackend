package com.example.EnterpriseProject.security.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(description = "Response payload after successful authentication")
public class LoginResponse {

    @Schema(description = "User unique primary key ID", example = "1")
    private Long id;

    @Schema(description = "Generated JWT Token string", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String jwtToken;

    @Schema(description = "User full name or display username", example = "Nguyễn Văn A")
    private String username;

    @Schema(description = "User card / badge ID", example = "2026-FII")
    private String cardId;

    @Schema(description = "List of assigned roles/authorities", example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]")
    private List<String> roles;


    public LoginResponse(Long id, String jwtToken, String username, String cardId, List<String> roles) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.cardId = cardId;
        this.roles = roles;
        this.id = id;
    }

}
