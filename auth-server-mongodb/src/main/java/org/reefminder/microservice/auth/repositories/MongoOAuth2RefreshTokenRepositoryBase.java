package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoOAuth2RefreshToken;

public interface MongoOAuth2RefreshTokenRepositoryBase {
    MongoOAuth2RefreshToken findByTokenId(String tokenId);

    boolean deleteByTokenId(String tokenId);
}
