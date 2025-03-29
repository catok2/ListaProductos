package com.Services.UtilsServices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para simplificar las pruebas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/productos/**").permitAll() // Permite acceso sin autenticación
                        .anyRequest().authenticated() // El resto de los endpoints requieren autenticación
                )
                .httpBasic(Customizer.withDefaults()); // Habilita la autenticación básica
        return http.build();
    }
}
