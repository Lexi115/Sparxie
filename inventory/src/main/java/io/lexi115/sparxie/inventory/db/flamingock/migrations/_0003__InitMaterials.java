package io.lexi115.sparxie.inventory.db.flamingock.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;
import io.lexi115.sparxie.inventory.db.flamingock.MigrationConstants;
import io.lexi115.sparxie.inventory.materials.Material;
import io.lexi115.sparxie.inventory.util.JsonHelper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = MigrationConstants.TARGET_SYSTEM)
@Change(id = "init-materials", author = MigrationConstants.AUTHOR)
public class _0003__InitMaterials {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var resourceStream = getClass().getResourceAsStream("/db/init/materials.json")) {
            var materialList = jsonHelper.parseCollection(resourceStream, Material.class);
            mongoTemplate.insertAll(materialList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(Material.class);
    }
}
