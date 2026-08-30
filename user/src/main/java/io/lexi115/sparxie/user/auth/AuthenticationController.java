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
    public UserRegisterResponse registerUser(@Valid @RequestBody final UserRegisterRequest request) {
        return authenticationService.registerUser(request.username(), request.password());
    }

    @PostMapping("/login")
    public UserLoginResponse authenticateUser(@Valid @RequestBody final UserLoginRequest request) {
        return authenticationService.authenticateUser(request.username(), request.password());
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshUserToken(@Valid @RequestBody final RefreshTokenRequest request) {
        return authenticationService.refreshUserToken(request.userId(), request.refreshToken());
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changeUserPassword(@Valid @RequestBody final ChangePasswordRequest request) {
        authenticationService.changeUserPassword(request.userId(), request.oldPassword(), request.newPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable final UUID userId) {
        authenticationService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
