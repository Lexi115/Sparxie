package io.lexi115.sparxie.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomRequestHeadersFilter customRequestHeadersFilter;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/change-password").authenticated()
                        .requestMatchers("/api/auth/jwk").denyAll()
                        .requestMatchers("/api/auth/refresh").authenticated()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(customRequestHeadersFilter, BearerTokenAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2
                        -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
