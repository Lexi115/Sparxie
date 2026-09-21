package io.lexi115.sparxie.logger.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCreatedRepository extends JpaRepository<UserCreated, UUID> {
}
