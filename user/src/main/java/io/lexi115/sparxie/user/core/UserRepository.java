package io.lexi115.sparxie.user.core;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);

    void save(User user);

    void deleteById(UUID id);
}
