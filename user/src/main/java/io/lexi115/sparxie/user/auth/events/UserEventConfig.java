package io.lexi115.sparxie.user.auth.events;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class UserEventConfig {
    @Value("${app.kafka.topic.user}")
    private String userTopicName;
}
