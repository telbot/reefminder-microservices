package org.reefminder.microservice.auth.mongo.builders;

import org.reefminder.microservice.auth.mongo.domain.MongoApproval;
import org.reefminder.microservice.auth.mongo.helpers.TestDataGenerator;
import org.springframework.security.oauth2.provider.approval.Approval;

import java.time.LocalDate;
import java.util.UUID;

public class MongoApprovalBuilder {

    private String id = UUID.randomUUID().toString();
    private String userId = TestDataGenerator.generateString().get();
    private String clientId = TestDataGenerator.generateString().get();
    private String scope = TestDataGenerator.generateString().get();
    private Approval.ApprovalStatus status = Approval.ApprovalStatus.DENIED;
    private LocalDate expiresAt = LocalDate.now();
    private LocalDate lastUpdatedAt = LocalDate.now();

    private MongoApprovalBuilder() {
    }

    public static MongoApprovalBuilder mongoApprovalBuilder() {
        return new MongoApprovalBuilder();
    }

    public MongoApproval build() {
        return new MongoApproval(id, userId, clientId, scope, status, expiresAt, lastUpdatedAt);
    }

    public MongoApprovalBuilder clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public MongoApprovalBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }
}
