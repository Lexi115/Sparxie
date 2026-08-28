package io.lexi115.sparxie.user.core;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import io.lexi115.sparxie.user.core.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponse registerUser(@Valid @RequestBody final RegisterRequest request) {
        return userService.registerUser(request.username(), request.password());
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody final RegisterRequest request) {
        return userService.loginUser(request.username(), request.password());
    }
}
