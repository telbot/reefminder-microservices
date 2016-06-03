package org.reefminder.microservice.auth.mongo.builders;

import com.google.common.base.Joiner;
import org.reefminder.microservice.auth.mongo.helpers.TestDataGenerator;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class ClientDetailsBuilder {

    private String clientId = TestDataGenerator.generateString().get();
    private String resourceIds = TestDataGenerator.generateDelimitedStringList().get();
    private String scopes = TestDataGenerator.generateDelimitedStringList().get();
    private String grantTypes = TestDataGenerator.generateDelimitedStringList().get();
    private String authorities = TestDataGenerator.generateDelimitedStringList().get();

    private ClientDetailsBuilder() {
    }

    public static ClientDetailsBuilder clientDetailsBuilder() {
        return new ClientDetailsBuilder();
    }

    public ClientDetails build() {
        return new BaseClientDetails(clientId, resourceIds, scopes, grantTypes, authorities);
    }
}
