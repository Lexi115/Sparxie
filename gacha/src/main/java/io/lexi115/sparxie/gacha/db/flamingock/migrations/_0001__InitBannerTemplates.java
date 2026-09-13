package io.lexi115.sparxie.gacha.db.flamingock.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;
import io.lexi115.sparxie.gacha.banners.Banner;
import io.lexi115.sparxie.gacha.db.flamingock.MigrationConstants;
import io.lexi115.sparxie.gacha.util.JsonHelper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = MigrationConstants.TARGET_SYSTEM)
@Change(id = "init-banner-templates", author = MigrationConstants.AUTHOR)
public class _0001__InitBannerTemplates {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var resourceStream = getClass().getResourceAsStream(
                MigrationConstants.BANNERS_PATH + "/banner_templates.json")) {
            var bannerList = jsonHelper.parseCollection(resourceStream, Banner.class);
            mongoTemplate.insertAll(bannerList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(Banner.class);
    }
}
