package com.example.EnterpriseProject.controller;

import com.example.EnterpriseProject.payload.APIResponse;
import com.example.EnterpriseProject.security.jwt.JwtUtils;
import com.example.EnterpriseProject.security.request.LoginRequest;
import com.example.EnterpriseProject.security.response.LoginResponse;
import com.example.EnterpriseProject.security.services.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "Authentication", description = "Endpoints for user login, logout, and token authentication")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwts;

    @Operation(summary = "User Login", description = "Authenticates user using cardId and password, returns JWT token and sets auth cookie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error in request body",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "401", description = "Bad credentials or authentication failure",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
            @ApiResponse(responseCode = "403", description = "User account disabled or locked",
                    content = @Content(schema = @Schema(implementation = APIResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCardId(),
                    loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwts.generateCookieFromJwt(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        LoginResponse loginResponse = new LoginResponse(userDetails.getId(), jwtCookie.toString(), userDetails.getUsername(), userDetails.getCardId(), roles);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(loginResponse);
    }

    @Operation(summary = "User Logout", description = "Clears the authentication cookie and logs out the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout successful",
                    content = @Content(schema = @Schema(implementation = APIResponse.class)))
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie responseCookie = jwts.cleanCookieFromJwt();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(new APIResponse(true, "Logout successfully"));
    }
}
