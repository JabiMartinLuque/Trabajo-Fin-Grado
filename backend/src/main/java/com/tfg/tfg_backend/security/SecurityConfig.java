package com.tfg.tfg_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilita CSRF (útil para APIs REST)
            .csrf(csrf -> csrf.disable())
            // Permite el acceso a los endpoints de autenticación y a la consola H2
            /*
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            */
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            // Deshabilita las cabeceras que impiden la visualización en frames (necesario para H2 console)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            // Usa autenticación básica para las demás rutas protegidas
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}