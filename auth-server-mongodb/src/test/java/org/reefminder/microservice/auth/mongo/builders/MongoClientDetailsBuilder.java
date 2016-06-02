package org.reefminder.microservice.auth.mongo.builders;

import com.google.common.collect.Sets;
import org.reefminder.microservice.auth.mongo.commons.SecurityRDG;
import org.springframework.security.core.GrantedAuthority;
import uk.co.caeldev.springsecuritymongo.domain.MongoClientDetails;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MongoClientDetailsBuilder {

    private String clientId = SecurityRDG.ofEscapedString().next();
    private String clientSecret = SecurityRDG.ofEscapedString().next();
    private Set<String> scope = set(SecurityRDG.ofEscapedString()).next();
    private Set<String> resourceIds = set(SecurityRDG.ofEscapedString()).next();
    private Set<String> authorizedGrantTypes = set(SecurityRDG.ofEscapedString()).next();
    private Set<String> registeredRedirectUris = set(SecurityRDG.ofEscapedString()).next();
    private List<GrantedAuthority> authorities = list(SecurityRDG.ofGrantedAuthority()).next();
    private Integer accessTokenValiditySeconds = integer().next();
    private Integer refreshTokenValiditySeconds = integer().next();
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
