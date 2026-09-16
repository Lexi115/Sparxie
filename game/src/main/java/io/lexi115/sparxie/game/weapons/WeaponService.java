package io.lexi115.sparxie.game.weapons;

import io.lexi115.sparxie.game.weapons.dto.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponClient weaponClient;

    public Weapon getById(final String weaponId) {
        return weaponClient.getById(weaponId);
    }
}
