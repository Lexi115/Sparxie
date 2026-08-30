package io.lexi115.sparxie.user.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);

    void save(User user);

    void delete(User user);
}
