package io.lexi115.sparxie.inventory.db;

import com.mongodb.client.MongoClient;
import io.flamingock.internal.core.external.store.AuditStore;
import io.flamingock.store.mongodb.sync.MongoDBSyncAuditStore;
import io.flamingock.targetsystem.mongodb.springdata.MongoDBSpringDataTargetSystem;
import io.flamingock.targetsystem.mongodb.sync.MongoDBSyncTargetSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class FlamingockConfig {

    @Value("${app.flamingock.target-system}")
    private String targetSystemId;

    @Bean
    public MongoDBSpringDataTargetSystem mongoDBSpringDataTargetSystem(MongoTemplate mongoTemplate) {
        return new MongoDBSpringDataTargetSystem("target-system-id", mongoTemplate);
    }

    @Bean
    public MongoDBSyncTargetSystem mongoDbSyncTargetSystem(MongoClient mongoClient) {
        return new MongoDBSyncTargetSystem("mongodb-id", mongoClient, "user_db");
    }

    @Bean
    public AuditStore auditStore(MongoDBSyncTargetSystem mongoDBSyncTargetSystem) {
        return MongoDBSyncAuditStore.from(mongoDBSyncTargetSystem);
    }
}
