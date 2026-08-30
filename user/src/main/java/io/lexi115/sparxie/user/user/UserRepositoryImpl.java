package io.lexi115.sparxie.user.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final HashMap<UUID, User> map = new HashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        map.remove(user.getId());
    }
}
