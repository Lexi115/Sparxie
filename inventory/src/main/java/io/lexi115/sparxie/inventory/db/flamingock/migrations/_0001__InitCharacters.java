package io.lexi115.sparxie.inventory.db.flamingock.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;
import io.lexi115.sparxie.inventory.characters.Character;
import io.lexi115.sparxie.inventory.db.flamingock.MigrationConstants;
import io.lexi115.sparxie.inventory.util.JsonHelper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = MigrationConstants.TARGET_SYSTEM)
@Change(id = "init-characters", author = MigrationConstants.AUTHOR)
public class _0001__InitCharacters {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var resourceStream = getClass().getResourceAsStream("/db/init/characters.json")) {
            var characterList = jsonHelper.parseCollection(resourceStream, Character.class);
            mongoTemplate.insertAll(characterList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(Character.class);
    }
}
