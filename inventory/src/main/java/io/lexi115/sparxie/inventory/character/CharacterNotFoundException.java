package io.lexi115.sparxie.inventory.character;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(final String characterId) {
        super("Character with ID '" + characterId + "' not found.");
    }
}
