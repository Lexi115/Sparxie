package io.lexi115.sparxie.user.user;

import io.lexi115.sparxie.user.auth.exception.UsernameAlreadyInUseException;
import io.lexi115.sparxie.user.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public void create(final UUID userId, final String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyInUseException(username);
        }
        var user = User.builder()
                .id(userId)
                .username(username)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(final UUID userId) {
        var user = getById(userId);
        userRepository.delete(user);
    }
}
