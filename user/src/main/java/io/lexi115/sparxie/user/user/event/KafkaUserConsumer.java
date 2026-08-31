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
@KafkaListener(topics = "${app.kafka.topic.user}", groupId = "${app.kafka.group.user}")
public class KafkaUserConsumer {
    private final UserService userService;

    @KafkaHandler
    public void onUserCreated(final UserCreatedEvent event) {
        System.out.println("user created event " + event.userId());
        userService.create(event.userId(), event.username());
    }

    @KafkaHandler
    public void onUserDeleted(final UserDeletedEvent event) {
        System.out.println("user deleted event " + event.userId());
        userService.deleteById(event.userId());
    }
}
