package org.reefminder.microservice.auth.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.reefminder.microservice.auth.mongo.builders.MongoOAuth2ClientTokenBuilder;
import org.reefminder.microservice.auth.mongo.builders.OAuth2AccessTokenBuilder;
import org.reefminder.microservice.auth.mongo.builders.OAuth2ProtectedResourceDetailsBuilder;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientKeyGenerator;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import uk.co.caeldev.springsecuritymongo.domain.MongoOAuth2ClientToken;
import uk.co.caeldev.springsecuritymongo.repositories.MongoOAuth2ClientTokenRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.reefminder.microservice.auth.mongo.builders.UserBuilder.userBuilder;
import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.string;

@RunWith(MockitoJUnitRunner.class)
public class MongoClientTokenServicesTest {

    @Mock
    private MongoOAuth2ClientTokenRepository mongoOAuth2ClientTokenRepository;

    @Mock
    private ClientKeyGenerator keyGenerator;

    private MongoClientTokenServices mongoClientTokenServices;

    @Before
    public void setup() {
        mongoClientTokenServices = new MongoClientTokenServices(mongoOAuth2ClientTokenRepository, keyGenerator);
    }

    @Test
    public void shouldSaveAccessToken() {
        //Given
        final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails = OAuth2ProtectedResourceDetailsBuilder.oAuth2ProtectedResourceDetailsBuilder().build();
        final TestingAuthenticationToken authentication = new TestingAuthenticationToken(userBuilder().build(), string().next());
        final OAuth2AccessToken oAuth2AccessToken = OAuth2AccessTokenBuilder.oAuth2AccessTokenBuilder().build();

        //And
        final String authenticationId = string().next();
        given(keyGenerator.extractKey(oAuth2ProtectedResourceDetails, authentication)).willReturn(authenticationId);

        //When
        mongoClientTokenServices.saveAccessToken(oAuth2ProtectedResourceDetails, authentication, oAuth2AccessToken);

        //Then
        verify(keyGenerator, atLeastOnce()).extractKey(oAuth2ProtectedResourceDetails, authentication);
        verify(mongoOAuth2ClientTokenRepository).save(any(MongoOAuth2ClientToken.class));
        verify(mongoOAuth2ClientTokenRepository).deleteByAuthenticationId(authenticationId);
    }

    @Test
    public void shouldRemoveAccessToken() {
        //Given
        final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails = OAuth2ProtectedResourceDetailsBuilder.oAuth2ProtectedResourceDetailsBuilder().build();
        final TestingAuthenticationToken authentication = new TestingAuthenticationToken(userBuilder().build(), string().next());

        //When
        mongoClientTokenServices.removeAccessToken(oAuth2ProtectedResourceDetails, authentication);

        //Then
        verify(keyGenerator).extractKey(oAuth2ProtectedResourceDetails, authentication);
        verify(mongoOAuth2ClientTokenRepository).deleteByAuthenticationId(anyString());
    }

    @Test
    public void shouldGetAccessToken() {
        //Given
        final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails = OAuth2ProtectedResourceDetailsBuilder.oAuth2ProtectedResourceDetailsBuilder().build();
        final TestingAuthenticationToken authentication = new TestingAuthenticationToken(userBuilder().build(), string().next());

        //And
        final String authenticationId = string().next();
        given(keyGenerator.extractKey(oAuth2ProtectedResourceDetails, authentication)).willReturn(authenticationId);

        //And
        final OAuth2AccessToken expectedToken = OAuth2AccessTokenBuilder.oAuth2AccessTokenBuilder().build();
        given(mongoOAuth2ClientTokenRepository.findByAuthenticationId(authenticationId)).willReturn(MongoOAuth2ClientTokenBuilder.mongoOAuth2ClientTokenBuilder().token(expectedToken).build());

        //When
        final OAuth2AccessToken accessToken = mongoClientTokenServices.getAccessToken(oAuth2ProtectedResourceDetails, authentication);

        //Then
        assertThat(accessToken).isEqualTo(expectedToken);
    }
}
