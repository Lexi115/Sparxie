package io.lexi115.sparxie.shop.db.flamingock;

import com.mongodb.client.MongoClient;
import io.flamingock.internal.core.external.store.AuditStore;
import io.flamingock.internal.core.external.store.audit.community.CommunityAuditPersistence;
import io.flamingock.store.mongodb.sync.MongoDBSyncAuditStore;
import io.flamingock.targetsystem.mongodb.springdata.MongoDBSpringDataTargetSystem;
import io.flamingock.targetsystem.mongodb.sync.MongoDBSyncTargetSystem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties("app.flamingock")
@Getter
@Setter
public class FlamingockConfig {

    private String database;

    @Bean
    public MongoDBSpringDataTargetSystem mongoDbSpringDataTargetSystem(MongoTemplate mongoTemplate) {
        return new MongoDBSpringDataTargetSystem(MigrationConstants.TARGET_SYSTEM, mongoTemplate);
    }

    @Bean
    public MongoDBSyncTargetSystem mongoDbSyncTargetSystem(MongoClient mongoClient) {
        return new MongoDBSyncTargetSystem(MigrationConstants.SYNC_SYSTEM, mongoClient, database);
    }

    @Bean
    public AuditStore<CommunityAuditPersistence> auditStore(MongoDBSyncTargetSystem mongoDBSyncTargetSystem) {
        return MongoDBSyncAuditStore.from(mongoDBSyncTargetSystem);
    }
}
