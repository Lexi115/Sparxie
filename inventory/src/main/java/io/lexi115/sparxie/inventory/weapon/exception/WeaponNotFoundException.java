package io.lexi115.sparxie.inventory.weapon.exception;

public class WeaponNotFoundException extends RuntimeException {
    public WeaponNotFoundException(final String message) {
        super(message);
    }
}
