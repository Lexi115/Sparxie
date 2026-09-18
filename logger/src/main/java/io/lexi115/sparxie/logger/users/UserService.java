package io.lexi115.sparxie.logger.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreatedRepository userCreatedRepository;
    private final UserDeletedRepository userDeletedRepository;

    public void createUserCreated(
            final UUID userId,
            final String username,
            final Instant createdAt,
            final String provider
    ) {
        var userCreated = UserCreated.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .username(username)
                .createdAt(createdAt)
                .provider(provider)
                .build();
        userCreatedRepository.save(userCreated);
    }

    public void createUserDeleted(
            final UUID userId,
            final Instant deletedAt
    ) {
        var userDeleted = UserDeleted.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .deletedAt(deletedAt)
                .build();
        userDeletedRepository.save(userDeleted);
    }
}
