package io.lexi115.sparxie.user.auth.admin.adapter.supabase;

import feign.RequestInterceptor;
import io.lexi115.sparxie.user.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SupabaseAdminClientConfig {

    private final JwtHelper jwtHelper;

    @Value("${app.supabase.jwt-secret}")
    private String jwtSecret;

    @Bean
    public RequestInterceptor adminTokenInterceptor() {
        return template -> {
            String token = generateServiceRoleToken();
            template.header("Authorization", "Bearer " + token);
        };
    }

    private String generateServiceRoleToken() {
        return jwtHelper.createToken(
                "HS256",
                null,
                Instant.now().plus(1, ChronoUnit.HOURS),
                Map.of(
                        "role", "service_role",
                        "iss", "supabase"
                ),
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );
    }
}
