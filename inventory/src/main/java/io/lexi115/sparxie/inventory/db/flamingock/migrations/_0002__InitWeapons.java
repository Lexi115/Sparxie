package io.lexi115.sparxie.inventory.db.flamingock.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;
import io.lexi115.sparxie.inventory.db.flamingock.MigrationConstants;
import io.lexi115.sparxie.inventory.util.JsonHelper;
import io.lexi115.sparxie.inventory.weapon.Weapon;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = MigrationConstants.TARGET_SYSTEM)
@Change(id = "init-weapons", author = MigrationConstants.AUTHOR)
public class _0002__InitWeapons {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var resourceStream = getClass().getResourceAsStream("/db/init/weapons.json")) {
            var weaponList = jsonHelper.parseCollection(resourceStream, Weapon.class);
            mongoTemplate.insertAll(weaponList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(Weapon.class);
    }
}
