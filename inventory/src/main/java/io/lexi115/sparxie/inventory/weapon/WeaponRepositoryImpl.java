package io.lexi115.sparxie.inventory.weapon;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class WeaponRepositoryImpl implements WeaponRepository {

    private final Map<String, Weapon> map = new HashMap<>(Map.of(
            "lc_earthly_escapade", new Weapon("lc_earthly_escapade", "Earthly Escapade")
    ));

    @Override
    public Optional<Weapon> findById(final String id) {
        return Optional.ofNullable(map.get(id));
    }
}
