package io.lexi115.sparxie.user.auth.dto;

public record Jwk(
        String kty,
        String use,
        String alg,
        String kid,
        String n,
        String e
) {
}
