package io.lexi115.sparxie.game.weapon;

import io.lexi115.sparxie.game.weapon.dto.WeaponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponClient weaponClient;

    public WeaponDto getById(final String weaponId) {
        return weaponClient.getById(weaponId);
    }
}
