package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;
import io.lexi115.sparxie.user.auth.exception.InvalidCredentialsException;
import io.lexi115.sparxie.user.auth.exception.UsernameAlreadyInUseException;
import io.lexi115.sparxie.user.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthenticationClientImpl implements AuthenticationClient {

    private final Map<UUID, AuthenticationUser> map = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(AuthenticationClientImpl.class);

    @Override
    public UserRegisterResponse registerUser(String username, String password) {
        map.forEach((_, user) -> {
            if (user.getUsername().equals(username)) {
                throw new UsernameAlreadyInUseException(username);
            }
        });

        // todo remove forced uuid
        var uuid = username.equals("sparkle") ? UUID.fromString("00000000-0000-0000-0000-000000000000") : UUID.randomUUID();

        var user = new AuthenticationUser(uuid, username, Instant.now(), password);
        map.put(user.getId(), user);
        logger.info("[AUTH] Registered user {}", user);
        return new UserRegisterResponse(user.getId());
    }

    @Override
    public UserLoginResponse authenticateUser(String username, String password) {
        for (var user : map.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                logger.info("[AUTH] Logged user {}", user);
                return new UserLoginResponse(user.getId(), "fake-access-token", "fake-refresh-token");
            }
        }
        throw new InvalidCredentialsException();
    }

    @Override
    public void deleteUser(UUID userId) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        map.remove(userId);
        logger.info("[AUTH] Deleted user with ID {}", userId);
    }

    @Override
    public RefreshTokenResponse refreshUserToken(UUID userId, String refreshToken) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        logger.info("[AUTH] Refreshed token for user {}", user);
        return new RefreshTokenResponse("fake-access-token");
    }

    @Override
    public void changeUserPassword(UUID userId, String oldPassword, String newPassword) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        if (!user.getPassword().equals(oldPassword)) {
            throw new InvalidCredentialsException("Old password provided doesn't match with current password!");
        }
        user.setPassword(newPassword);
        logger.info("[AUTH] Password changed for user {}", user);
    }

    @Override
    public JwkResponse getPublicJwk() {
        var jwk = new Jwk(
                "RSA",
                "sig",
                "RS256",
                "2026-key-1",
                "u1SU1LfVLPHCozMxH2Mo4lgOEePzNm0tRgeLezV6ffAt0gunVTLw7onLRnrq0_IzW7yWR7QkrmBL7jTKEn5u-qKhbwKfBstIs-bMY2Zkp18gnTxKLxoS2tFczGkPLPgizskuemMghRniWaoLcyehkd3qqGElvW_VDL5AaWTg0nLVkjRo9z-40RQzuVaE8AkAFmxZzow3x-VJYKdjykkJ0iT9wCS0DRTXu269V264Vf_3jvredZiKRkgwlL9xNAwxXFg0x_XFw005UWVRIkdgcKWTjpBP2dPwVZ4WWC-9aGVd-Gyn1o0CLelf4rEjGoXbAAEgAqeGUxrcIlbjXfbcmw",
                "AQAB"
        );
        return new JwkResponse(List.of(jwk));
    }
}
