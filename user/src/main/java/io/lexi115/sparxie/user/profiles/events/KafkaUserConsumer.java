package io.lexi115.sparxie.user.profiles.events;

import io.lexi115.sparxie.user.auth.events.UserCreatedEvent;
import io.lexi115.sparxie.user.auth.events.UserDeletedEvent;
import io.lexi115.sparxie.user.profiles.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${app.kafka.topic.user}", groupId = "${app.kafka.group.user}")
public class KafkaUserConsumer {
    private final ProfileService profileService;

    @KafkaHandler
    public void onUserCreated(final UserCreatedEvent event) {
        var userId = event.userId();
        if (!profileService.existsById(userId)) {
            profileService.create(userId, event.username());
        }
    }

    @KafkaHandler
    public void onUserDeleted(final UserDeletedEvent event) {
        profileService.deleteById(event.userId());
    }
}
