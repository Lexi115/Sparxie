package io.lexi115.sparxie.game.materials;

import io.lexi115.sparxie.game.materials.dto.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/{materialId}")
    public Material getById(@PathVariable final String materialId) {
        return materialService.getById(materialId);
    }
}
