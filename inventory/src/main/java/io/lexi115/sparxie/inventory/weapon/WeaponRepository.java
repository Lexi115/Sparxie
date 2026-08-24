package io.lexi115.sparxie.inventory.weapon;

import java.util.Optional;

public interface WeaponRepository {
    Optional<Weapon> findById(String id);

    boolean existsById(String id);
}
