package io.lexi115.sparxie.user.user.event;

import io.lexi115.sparxie.user.auth.event.UserCreatedEvent;
import io.lexi115.sparxie.user.auth.event.UserDeletedEvent;
import io.lexi115.sparxie.user.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "user-topic", groupId = "user-service-group")
public class UserEventConsumer {
    private final UserService userService;

    @KafkaHandler
    public void onUserCreated(final UserCreatedEvent event) {
        System.out.println("player created event");
        userService.createUser(event.userId(), event.username(), event.createdAt());
    }

    @KafkaHandler
    public void onUserDeleted(final UserDeletedEvent event) {
        System.out.println("player deleted event");
        userService.deleteUser(event.userId());
    }
}
