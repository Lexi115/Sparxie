package io.lexi115.sparxie.user.auth.admin;

import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/admin")
@RequiredArgsConstructor
public class AdminAuthenticationController {
    private final AdminAuthenticationService adminAuthenticationService;

    @PostMapping("/users")
    public AdminCreateUserResponse createUser(@Valid @RequestBody final AdminCreateUserRequest request) {
        return adminAuthenticationService.createUser(request);
    }

    @PutMapping("/users/{userId}/password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody final AdminUpdatePasswordRequest request,
            @PathVariable final UUID userId
    ) {
        adminAuthenticationService.updatePassword(request, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable final UUID userId) {
        adminAuthenticationService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
