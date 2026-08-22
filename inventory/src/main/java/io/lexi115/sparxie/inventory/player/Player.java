package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.character.Character;
import io.lexi115.sparxie.inventory.material.Material;
import io.lexi115.sparxie.inventory.weapon.Weapon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Getter
public class Player {
    private final UUID id;
    private final Map<String, PlayerCharacter> characters = new HashMap<>();
    private final List<PlayerWeapon> weapons = new ArrayList<>();
    private final Map<String, Long> materials = new HashMap<>();

    public void giveCharacter(final Character character) {
        characters.computeIfAbsent(character.id(), PlayerCharacter::new).incrementCopies();
    }

    public void giveWeapon(final Weapon weapon) {
        weapons.add(new PlayerWeapon(weapon.id()));
    }

    public void giveMaterial(final Material material, final Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        var materialId = material.name();
        materials.put(materialId, materials.getOrDefault(materialId, 0L) + amount);
    }
}
