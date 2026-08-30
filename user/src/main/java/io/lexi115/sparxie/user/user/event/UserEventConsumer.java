package io.lexi115.sparxie.user.user.event;

import io.lexi115.sparxie.user.auth.event.UserCreatedEvent;
import io.lexi115.sparxie.user.auth.event.UserDeletedEvent;
import io.lexi115.sparxie.user.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventConsumer {
    private final UserService userService;

    public void onUserCreated(final UserCreatedEvent event) {
        System.out.println("player created event");
        userService.createUser(event.userId(), event.username(), event.createdAt());
    }

    public void onUserDeleted(final UserDeletedEvent event) {
        System.out.println("player deleted event");
        userService.deleteUser(event.userId());
    }
}
