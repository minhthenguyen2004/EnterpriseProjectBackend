package com.example.EnterpriseProject.security.jwt;

import com.example.EnterpriseProject.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    //To get the log
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtExpirationTime}")
    private int jwtExpirationTime;

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.ecom.app.jwtCookieName}")
    private String jwtCookie;


    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization header: {}", bearerToken);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public String getJwtFromCookies(HttpServletRequest httpServletRequest) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtCookie);

        if (cookie != null) {
            //It is the token
            return cookie.getValue();
        }
        return null;
    }


    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime() + jwtExpirationTime)))
                .signWith(key())
                .compact();
    }


    public ResponseCookie generateCookieFromJwt(UserDetailsImpl userDetailsImpl) {
        String identifier = userDetailsImpl.getUsername();
        if (identifier == null || identifier.isBlank()) {
            identifier = userDetailsImpl.getCardId();
        }
        String jwt = generateTokenFromUsername(identifier);
        ResponseCookie responseCookie = ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60)
                .httpOnly(false)
                .secure(false) //on localhost (http) is not a secure page, set false to help it operate
                .build();
        return responseCookie;
    }


    public ResponseCookie cleanCookieFromJwt() {
        ResponseCookie responseCookie = ResponseCookie.from(jwtCookie, null)
                .path("/api")
                .build();
        return responseCookie;
    }


    public String getUsernameFromJWTToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public boolean validateJwtToken(String authToken) {
        try {
            System.out.println("Validate");
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);

            return true;
        } catch (MalformedJwtException e) {
            logger.error("MalformedJwtException: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("ExpiredJwtException: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("UnsupportedJwtException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException: {}", e.getMessage());
        }

        return false;
    }
}
