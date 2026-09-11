package io.lexi115.sparxie.gateway.security;

import io.lexi115.sparxie.gateway.security.filters.JwtAuthorizationFilter;
import io.lexi115.sparxie.gateway.security.filters.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final List<SecurityFilter> securityFilters;

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    securityFilters.forEach(securityFilter -> securityFilter.filter(auth));
                    auth.anyRequest().authenticated();
                })
                .addFilterAfter(jwtAuthorizationFilter, BearerTokenAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> {
                            jwt.decoder(jwtDecoder());
                            jwt.jwtAuthenticationConverter(supabaseJwtAuthenticationConverter());
                        }));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        var originalKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(originalKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public JwtAuthenticationConverter supabaseJwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var appMetadata = jwt.getClaimAsMap("app_metadata");
            if (appMetadata == null || !appMetadata.containsKey("roles")) {
                return Collections.emptyList();
            }
            @SuppressWarnings("unchecked")
            var roleList = (List<String>) appMetadata.get("roles");
            return new ArrayList<>(roleList.stream().map(
                    role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).toList());
        });
        return converter;
    }
}
