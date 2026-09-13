package io.lexi115.sparxie.game.materials;

import io.lexi115.sparxie.game.materials.dto.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialClient materialClient;

    public Material getById(final String materialId) {
        return materialClient.getById(materialId);
    }
}
