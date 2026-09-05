package io.lexi115.sparxie.gateway.security;

import io.lexi115.sparxie.gateway.http.CustomHttpServletRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var principal = (Jwt) authentication.getPrincipal();
        if (principal == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var customRequest = new CustomHttpServletRequestWrapper(request);
        var userId = principal.getSubject();
        customRequest.addHeader("X-User-Id", userId);
        filterChain.doFilter(customRequest, response);
    }
}
