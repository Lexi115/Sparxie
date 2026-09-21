package io.lexi115.sparxie.logger.users.events;

import io.lexi115.sparxie.logger.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${app.kafka.topic.user}", groupId = "${app.kafka.group.logger}")
public class KafkaUserConsumer {
    private final UserService userService;

    @KafkaHandler
    public void onUserCreated(final UserCreatedEvent event) {
        userService.createUserCreated(event.userId(), event.username(), event.createdAt(), event.provider());
    }

    @KafkaHandler
    public void onUserDeleted(final UserDeletedEvent event) {
        userService.createUserDeleted(event.userId(), event.deletedAt());
    }
}
