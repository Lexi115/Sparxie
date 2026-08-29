package io.lexi115.sparxie.user.core;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import io.lexi115.sparxie.user.core.dto.ChangePasswordRequest;
import io.lexi115.sparxie.user.core.dto.RefreshTokenRequest;
import io.lexi115.sparxie.user.core.dto.RegisterRequest;
import io.lexi115.sparxie.user.core.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final UUID id) {
        var user = userService.getById(id);
        return new UserDto(user.getId(), user.getUsername(), user.getCreatedAt());
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(@Valid @RequestBody final RegisterRequest request) {
        return userService.registerUser(request.username(), request.password());
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody final RegisterRequest request) {
        return userService.loginUser(request.username(), request.password());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refreshToken")
    public RefreshTokenResponse refreshUserToken(@Valid @RequestBody final RefreshTokenRequest request) {
        return userService.refreshUserToken(request.userId(), request.refreshToken());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody final ChangePasswordRequest request) {
        userService.changePassword(request.userId(), request.oldPassword(), request.newPassword());
        return ResponseEntity.noContent().build();
    }
}
