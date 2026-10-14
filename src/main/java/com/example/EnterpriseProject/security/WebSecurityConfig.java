package com.example.EnterpriseProject.security;

import com.example.EnterpriseProject.security.jwt.AuthEntryPoint;
import com.example.EnterpriseProject.security.jwt.AuthTokenFilter;
import com.example.EnterpriseProject.security.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests)

                -> requests.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-resources/**", "/configuration/**", "/webjars/**").permitAll()
                .requestMatchers("/favicon.ico", "/error").permitAll()
                .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers("/api/ODMMachine/**", "/test1/api/ODMMachine/**").permitAll()

                //Test
                .requestMatchers("/api/machines/**", "/api/simulator/**").permitAll()

                .requestMatchers("/test1/api/machines/**", "/test1/api/simulator/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .anyRequest().authenticated());

        http.csrf(csrf -> csrf.disable());


        //Give errors when there are any revelent errors related to security
        http.exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(unauthorizedHandler);
                }
        );

        http.authenticationProvider(daoAuthenticationProvider());


        http.headers(headers ->
                headers.frameOptions(frameOptions ->
                        frameOptions.sameOrigin()));

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        http.sessionManagement(session
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //It helps not to save session or cookies -> cannot see the cookie part when pressing F12

        return http.build();
    }

}
