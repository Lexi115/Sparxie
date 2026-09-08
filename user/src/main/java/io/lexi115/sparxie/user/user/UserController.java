package io.lexi115.sparxie.user.user;

import io.lexi115.sparxie.user.user.dto.UserDto;
import io.lexi115.sparxie.user.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public UserDto getAuthenticatedUser(@RequestHeader("X-User-Id") final UUID userId) {
        var user = userService.getById(userId);
        return userMapper.toDto(user);
    }

//    @GetMapping("/{userId}")
//    public UserDto getById(@PathVariable final UUID userId) {
//        var user = userService.getById(userId);
//        return userMapper.toDto(user);
//    }
}
