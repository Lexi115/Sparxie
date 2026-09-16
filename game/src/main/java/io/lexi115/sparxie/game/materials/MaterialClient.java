package io.lexi115.sparxie.game.materials;

import io.lexi115.sparxie.game.materials.dto.Material;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "inventory",
        contextId = "materialClient",
        url = "${app.http.client-uri.materials}",
        path = "/materials"
)
public interface MaterialClient {
    @GetMapping("/{materialId}")
    Material getById(@PathVariable String materialId);
}
