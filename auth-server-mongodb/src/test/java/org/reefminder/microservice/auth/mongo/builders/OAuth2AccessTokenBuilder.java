package org.reefminder.microservice.auth.mongo.builders;

import org.joda.time.LocalDate;
import org.reefminder.microservice.auth.mongo.commons.SecurityRDG;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.util.Map;
import java.util.Set;

import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.string;
import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.map;
import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.set;
import static uk.org.fyodor.jodatime.generators.RDG.localDate;

public class OAuth2AccessTokenBuilder {

    private LocalDate expiration = localDate().next();
    private OAuth2RefreshToken oAuth2RefreshToken = OAuth2RefreshTokenBuilder.oAuth2RefreshToken().build();
    private Set<String> scope = set(string()).next();
    private Map<String, Object> additionalInformation = map(string(), SecurityRDG.objectOf(string())).next();
    private String token = string().next();

    private OAuth2AccessTokenBuilder() {
    }

    public static OAuth2AccessTokenBuilder oAuth2AccessTokenBuilder() {
        return new OAuth2AccessTokenBuilder();
    }

    public OAuth2AccessToken build() {
        final DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(token);
        oAuth2AccessToken.setExpiration(expiration.toDate());
        oAuth2AccessToken.setRefreshToken(oAuth2RefreshToken);
        oAuth2AccessToken.setScope(scope);
        oAuth2AccessToken.setAdditionalInformation(additionalInformation);
        return oAuth2AccessToken;
    }

    public OAuth2AccessTokenBuilder token(String token) {
        this.token = token;
        return this;
    }
}
