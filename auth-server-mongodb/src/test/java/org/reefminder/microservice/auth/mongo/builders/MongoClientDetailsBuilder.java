package org.reefminder.microservice.auth.mongo.builders;

import com.google.common.collect.Sets;
import org.reefminder.microservice.auth.mongo.helpers.TestDataGenerator;
import org.springframework.security.core.GrantedAuthority;
import static org.springframework.security.crypto.keygen.KeyGenerators.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MongoClientDetailsBuilder {

    private String clientId = TestDataGenerator.generateString().get();
    private String clientSecret = TestDataGenerator.generateString().get();
    private Set<String> scope = TestDataGenerator.generateDelimitedStringSet().get();
    private Set<String> resourceIds = TestDataGenerator.generateDelimitedStringSet().get();
    private Set<String> authorizedGrantTypes = TestDataGenerator.generateDelimitedStringSet().get();
    private Set<String> registeredRedirectUris = TestDataGenerator.generateDelimitedStringSet().get();
    private List<GrantedAuthority> authorities = list(SecurityRDG.ofGrantedAuthority()).next();
    private Integer accessTokenValiditySeconds = TestDataGenerator.generateInteger().get();
    private Integer refreshTokenValiditySeconds = TestDataGenerator.generateInteger().get();
    private Map<String, Object> additionalInformation = map(SecurityRDG.ofEscapedString(), SecurityRDG.objectOf(SecurityRDG.ofEscapedString())).next();

    private Set<String> autoApproveScopes = Sets.newHashSet("true");

    private MongoClientDetailsBuilder() {
    }

    public static MongoClientDetailsBuilder mongoClientDetailsBuilder() {
        return new MongoClientDetailsBuilder();
    }

    public MongoClientDetails build() {
        return new MongoClientDetails(clientId,
                clientSecret,
                scope,
                resourceIds,
                authorizedGrantTypes,
                registeredRedirectUris,
                authorities,
                accessTokenValiditySeconds,
                refreshTokenValiditySeconds,
                additionalInformation,
                autoApproveScopes);
    }
}
