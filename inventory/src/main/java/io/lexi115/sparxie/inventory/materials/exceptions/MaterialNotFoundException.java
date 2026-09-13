package io.lexi115.sparxie.inventory.materials.exceptions;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(final String materialId) {
        super("Material with ID '" + materialId + "' not found.");
    }
}
