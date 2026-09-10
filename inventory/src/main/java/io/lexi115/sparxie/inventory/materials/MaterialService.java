package io.lexi115.sparxie.inventory.materials;

import io.lexi115.sparxie.inventory.materials.exception.MaterialNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    @Cacheable(value = "materials", key = "'char_' + #id")
    public Material getById(final String id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException(id));
    }

    @Cacheable(value = "materials", key = "'exists_' + #id")
    public boolean existsById(final String id) {
        return materialRepository.existsById(id);
    }
}
