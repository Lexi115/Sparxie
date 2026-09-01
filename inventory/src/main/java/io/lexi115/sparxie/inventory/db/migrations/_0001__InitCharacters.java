package io.lexi115.sparxie.inventory.db.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;
import io.lexi115.sparxie.inventory.character.Character;
import io.lexi115.sparxie.inventory.util.JsonHelper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = "target-system-id")
@Change(id = "init-characters", author = "Lexi115")
public class _0001__InitCharacters {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var charactersResourceStream = getClass().getResourceAsStream("/db/init/characters.json")) {
            var charactersList = jsonHelper.parseCollection(charactersResourceStream, Character.class);
            mongoTemplate.insertAll(charactersList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(Character.class);
    }
}
