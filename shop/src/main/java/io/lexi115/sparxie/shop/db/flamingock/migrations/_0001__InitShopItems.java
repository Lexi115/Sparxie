package io.lexi115.sparxie.shop.db.flamingock.migrations;

import io.flamingock.api.annotations.Apply;
import io.flamingock.api.annotations.Change;
import io.flamingock.api.annotations.Rollback;
import io.flamingock.api.annotations.TargetSystem;

import io.lexi115.sparxie.shop.db.flamingock.MigrationConstants;
import io.lexi115.sparxie.shop.shop.items.ShopItem;
import io.lexi115.sparxie.shop.util.JsonHelper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TargetSystem(id = MigrationConstants.TARGET_SYSTEM)
@Change(id = "init-shop-items", author = MigrationConstants.AUTHOR)
public class _0001__InitShopItems {

    @Apply
    public void apply(MongoTemplate mongoTemplate, JsonHelper jsonHelper) throws IOException {
        try (var resourceStream = getClass().getResourceAsStream("/db/init/shop_items.json")) {
            var shopItemList = jsonHelper.parseCollection(resourceStream, ShopItem.class);
            mongoTemplate.insertAll(shopItemList);
        }
    }

    @Rollback
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(ShopItem.class);
    }
}
