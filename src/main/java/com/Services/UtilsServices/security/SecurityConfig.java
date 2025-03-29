package com.Services.UtilsServices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.security.oauth2.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String FIXED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1MTIzNDU2OCwiZXhwIjoxNjUxMjM4MTY4fQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/publico/**").permitAll()  // Ruta pública
                        .anyRequest().authenticated()  // Todas las demás requieren autenticación
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder())))
                .csrf(csrf -> csrf.disable()); // Opcional: Desactiva CSRF para APIs

        return http.build();
    }

    // 🔥 Mock del JwtDecoder que solo acepta el token fijo
    @Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            if (!FIXED_TOKEN.equals(token)) {
                throw new JwtException("Token inválido");
            }

            // Construye un JWT mockeado con los claims del token fijo
            return Jwt.withTokenValue(token)
                    .header("alg", "HS256")
                    .claim("sub", "admin")  // Usuario autenticado
                    .claim("scope", "ROLE_ADMIN")  // Rol (puedes usar "roles" u otros claims)
                    .build();
        };
    }
}
