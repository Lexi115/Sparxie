package io.lexi115.sparxie.user.user;

import io.lexi115.sparxie.user.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable final UUID userId) {
        var user = userService.getById(userId);
        return new UserDto(user.getId(), user.getUsername(), user.getCreatedAt());
    }
}
