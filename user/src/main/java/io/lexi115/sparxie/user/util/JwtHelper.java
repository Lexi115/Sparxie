package io.lexi115.sparxie.user.util;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class JwtHelper {
    private final ObjectMapper objectMapper;

    public <T> T extractClaim(final String token, final String claimName, final Class<T> targetClass) {
        var jwt = JWT.decode(token);
        return jwt.getClaim(claimName).as(targetClass);
    }

    public <T> T extractNestedClaim(final String token, final String claimName, final Class<T> targetClass) {
        var jwt = JWT.decode(token);
        var claimMap = jwt.getClaim(claimName).asMap();
        return objectMapper.convertValue(claimMap, targetClass);
    }
}
