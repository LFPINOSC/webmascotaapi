package com.mascotas.web.Seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth

                // *** RUTAS PÚBLICAS ***
                .requestMatchers(
                        "/api/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                // *** RUTAS PÚBLICAS DE UBICACIÓN ***
                .requestMatchers("/api/ubicaciones", "/api/ubicaciones/**").permitAll()

                // *** SOLO ADMIN ***
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                .requestMatchers("/api/razas/**").hasRole("ADMIN")

                // *** ADMIN + VETERINARIO ***
                .requestMatchers("/api/citas/**").hasAnyRole("ADMIN", "VETERINARIO")

                // *** DUEÑOS y ADMIN ***
                .requestMatchers("/api/mascotas/**").authenticated()

                // CUALQUIER OTRA REQUIERE TOKEN
                .anyRequest().authenticated()
        );

        // Inserta el filtro JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
