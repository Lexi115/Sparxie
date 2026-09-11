package io.lexi115.sparxie.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Map;

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

    public String createToken(
            final String algorithmName,
            final Object subject,
            final Instant expiresAt,
            final Map<String, Object> claims,
            final byte[] secret
    ) {
        var algorithm = createAlgorithm(algorithmName, secret);
        var jwt = JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(expiresAt)
                .withHeader(Map.of(
                        "alg", algorithm,
                        "typ", "JWT"
                ));
        claims.forEach((key, value) -> jwt.withClaim(key, value.toString()));
        JWTCreator.Builder finalJwt = jwt;
        if (subject != null) {
            finalJwt = jwt.withSubject(subject.toString());
        }
        return finalJwt.sign(algorithm);
    }

    private Algorithm createAlgorithm(final String algorithmName, final byte[] secret) {
        return switch (algorithmName) {
            case "HS256" -> Algorithm.HMAC256(secret);
            case "HS384" -> Algorithm.HMAC384(secret);
            case "HS512" -> Algorithm.HMAC512(secret);
            default -> Algorithm.none();
        };
    }
}
