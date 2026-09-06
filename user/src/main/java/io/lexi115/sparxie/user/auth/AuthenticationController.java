package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody final RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody final LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@Valid @RequestBody final RefreshTokenRequest request) {
        return authenticationService.refreshToken(request);
    }

    @PostMapping("/update-password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody final UpdatePasswordRequest request,
            @RequestHeader("Authorization") final String bearerToken
    ) {
        authenticationService.updatePassword(request, bearerToken);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/admin/change-password")
//    public ResponseEntity<Void> adminUpdatePassword(
//            @Valid @RequestBody final UpdatePasswordRequest request,
//            @RequestHeader("X-User-Id") final UUID userId
//    ) {
//        authenticationService.adminUpdatePassword(request, userId);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestHeader("X-User-Id") final UUID userId) {
        authenticationService.adminDelete(userId);
        return ResponseEntity.noContent().build();
    }
}
