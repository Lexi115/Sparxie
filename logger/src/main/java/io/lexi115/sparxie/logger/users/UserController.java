package io.lexi115.sparxie.logger.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warps")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
