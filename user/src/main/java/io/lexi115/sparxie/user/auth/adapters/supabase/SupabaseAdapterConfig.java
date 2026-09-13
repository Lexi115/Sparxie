package io.lexi115.sparxie.user.auth.adapters.supabase;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SupabaseAdapterConfig {
    @Value("${app.http.client-uri.supabase-external}")
    private String supabaseExternalClientUri;
}
