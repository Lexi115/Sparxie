package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.character.Character;
import io.lexi115.sparxie.inventory.weapon.Weapon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Player {
    private final UUID id;
    private final List<PlayerCharacter> characters = new ArrayList<>();
    private final List<PlayerWeapon> weapons = new ArrayList<>();

    public void giveCharacter(final Character character) {
        var it = characters.iterator();
        PlayerCharacter existingPlayerCharacter;
        while (it.hasNext()) {
            existingPlayerCharacter = it.next();
            if (existingPlayerCharacter.getCharacterId().equals(character.id())) {
                existingPlayerCharacter.incrementCopies();
                return;
            }
        }
        characters.add(new PlayerCharacter(character.id()));
    }

    public void giveWeapon(final Weapon weapon) {
        var it = weapons.iterator();
        PlayerWeapon existingPlayerWeapon;
        while (it.hasNext()) {
            existingPlayerWeapon = it.next();
            if (existingPlayerWeapon.getWeaponId().equals(weapon.id())) {
                existingPlayerWeapon.incrementCopies();
                return;
            }
        }
        weapons.add(new PlayerWeapon(weapon.id()));
    }
}
