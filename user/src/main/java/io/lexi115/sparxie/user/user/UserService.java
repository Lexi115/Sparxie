package io.lexi115.sparxie.user.user;

import io.lexi115.sparxie.user.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public void createUser(final UUID userId, final String username, final Instant createdAt) {
        var user = new User(userId, username, createdAt);
        userRepository.save(user);
    }

    public void deleteUser(final UUID userId) {
        var user = getById(userId);
        userRepository.delete(user);
    }
}
