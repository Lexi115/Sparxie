package io.lexi115.sparxie.user.auth.admin;

import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminViewUserResponse;

import java.util.UUID;

public interface AdminAuthenticationAdapter {
    AdminViewUserResponse viewUser(UUID userId);

    AdminCreateUserResponse createUser(AdminCreateUserRequest request);

    void updatePassword(AdminUpdatePasswordRequest request, UUID userId);

    void deleteUser(UUID userId);
}
