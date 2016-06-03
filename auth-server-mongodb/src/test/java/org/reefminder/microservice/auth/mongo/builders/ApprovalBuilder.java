package org.reefminder.microservice.auth.mongo.builders;

import org.reefminder.microservice.auth.mongo.helpers.TestDataGenerator;
import org.springframework.security.oauth2.provider.approval.Approval;

public class ApprovalBuilder {

    private String userId = TestDataGenerator.generateString().get();
    private String clientId = TestDataGenerator.generateString().get();
    private String scope = TestDataGenerator.generateString().get();
    private Integer expiresIn = TestDataGenerator.generateInteger().get();;
    private Approval.ApprovalStatus status = Approval.ApprovalStatus.APPROVED;

    private ApprovalBuilder() {
    }

    public static ApprovalBuilder approvalBuilder() {
        return new ApprovalBuilder();
    }

    public Approval build() {
        return new Approval(userId, clientId, scope, expiresIn, status);
    }
}
