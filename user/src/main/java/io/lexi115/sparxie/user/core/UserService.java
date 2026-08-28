package io.lexi115.sparxie.user.core;

import io.lexi115.sparxie.user.auth.AuthenticationService;
import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationService authenticationService;

    public RegisterResponse registerUser(final String username, final String password) {
        return authenticationService.registerUser(username, password);
    }

    public LoginResponse loginUser(final String username, final String password) {
        return authenticationService.loginUser(username, password);
    }
}
