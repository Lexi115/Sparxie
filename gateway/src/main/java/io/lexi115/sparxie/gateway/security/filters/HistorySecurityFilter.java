package io.lexi115.sparxie.gateway.security.filters;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class HistorySecurityFilter implements SecurityFilter {

    @Override
    public void filter(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        var prefix = "/api/histories";
        registry
                .requestMatchers(HttpMethod.GET, prefix + "/warps/{bannerType}").authenticated()
                .requestMatchers(HttpMethod.GET, prefix + "/purchases").authenticated();
    }
}
