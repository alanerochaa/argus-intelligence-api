package br.com.fiap.argus.config;

import br.com.fiap.argus.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Públicos
                        .requestMatchers(
                                "/",

                                "/css/**",
                                "/images/**",
                                "/js/**",
                                "/static/**",

                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",

                                "/actuator/**",

                                "/api/auth/**",

                                // DEMO / GS - endpoints públicos para validação
                                "/api/ingestao/**",
                                "/api/riscos/**",
                                "/api/ia/**",
                                "/api/chatbot/**",
                                "/api/inteligencia/**",
                                "/api/nasa/**",
                                "/api/weather/**"
                        ).permitAll()

                        // CRUD protegido
                        .requestMatchers(
                                "/api/biomas/**",
                                "/api/regioes/**",
                                "/api/focos/**",
                                "/api/alertas/**"
                        ).authenticated()

                        .requestMatchers("/api/**").authenticated()

                        .anyRequest().permitAll()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}