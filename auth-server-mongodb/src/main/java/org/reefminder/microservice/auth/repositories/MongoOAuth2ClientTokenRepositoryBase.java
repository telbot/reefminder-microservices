package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoOAuth2ClientToken;

public interface MongoOAuth2ClientTokenRepositoryBase {
    boolean deleteByAuthenticationId(String authenticationId);

    MongoOAuth2ClientToken findByAuthenticationId(String authenticationId);
}
