package io.lexi115.sparxie.inventory.materials;

import io.lexi115.sparxie.inventory.materials.dto.MaterialDto;
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
    public MaterialDto getById(@PathVariable final String materialId) {
        var material = materialService.getById(materialId);
        return new MaterialDto(material.getId(), material.getName(), material.getRarity().getValue());
    }
}
