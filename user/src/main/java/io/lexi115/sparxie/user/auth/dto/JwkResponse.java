package io.lexi115.sparxie.user.auth.dto;

import java.util.List;

public record JwkResponse(
        List<Jwk> keys
) {
}
