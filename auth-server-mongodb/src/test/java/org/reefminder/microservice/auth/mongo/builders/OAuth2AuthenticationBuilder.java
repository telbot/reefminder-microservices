package org.reefminder.microservice.auth.mongo.builders;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.string;
import static org.reefminder.microservice.auth.mongo.builders.OAuth2RequestBuilder.oAuth2RequestBuilder;

public class OAuth2AuthenticationBuilder {

    private OAuth2AuthenticationBuilder() {
    }

    public static OAuth2AuthenticationBuilder oAuth2AuthenticationBuilder() {
        return new OAuth2AuthenticationBuilder();
    }

    public OAuth2Authentication build() {
        return new OAuth2Authentication(oAuth2RequestBuilder().build(), new TestingAuthenticationToken(UserBuilder.userBuilder().build(), string().next()));
    }
}
