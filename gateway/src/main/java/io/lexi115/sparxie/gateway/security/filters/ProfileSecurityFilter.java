package io.lexi115.sparxie.gateway.security.filters;

import io.lexi115.sparxie.gateway.auth.UserRole;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ProfileSecurityFilter implements SecurityFilter {

    @Override
    public void filter(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        var prefix = "/api/profiles";
        registry
                .requestMatchers(prefix + "/admin/**").hasRole(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET, prefix + "/me").authenticated();
    }
}
