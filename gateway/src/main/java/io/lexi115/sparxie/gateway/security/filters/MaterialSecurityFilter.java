package io.lexi115.sparxie.gateway.security.filters;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class MaterialSecurityFilter implements SecurityFilter {

    @Override
    public void filter(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        var prefix = "/api/materials";
        registry
                .requestMatchers(HttpMethod.GET, prefix + "/{materialId}").permitAll();
    }
}
