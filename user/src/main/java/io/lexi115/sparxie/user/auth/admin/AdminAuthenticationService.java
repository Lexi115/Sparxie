package io.lexi115.sparxie.user.auth.admin;

import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminViewUserResponse;
import io.lexi115.sparxie.user.auth.event.UserEventService;
import io.lexi115.sparxie.user.auth.provider.IdentityProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminAuthenticationService {
    private final AdminAuthenticationAdapter adminAuthenticationAdapter;
    private final UserEventService userEventService;

    public AdminViewUserResponse viewUser(final UUID userId) {
        return adminAuthenticationAdapter.viewUser(userId);
    }

    public AdminCreateUserResponse createUser(final AdminCreateUserRequest request) {
        var response = adminAuthenticationAdapter.createUser(request);
        userEventService.userCreated(response.userId(), response.username(), response.createdAt(), IdentityProvider.EMAIL);
        return response;
    }

    public void updatePassword(final AdminUpdatePasswordRequest request, final UUID userId) {
        adminAuthenticationAdapter.updatePassword(request, userId);
    }

    public void deleteUser(final UUID userId) {
        adminAuthenticationAdapter.deleteUser(userId);
        userEventService.userDeleted(userId, Instant.now());
    }
}
