package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoOAuth2RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOAuth2RefreshTokenRepository extends MongoRepository<MongoOAuth2RefreshToken, String>, MongoOAuth2RefreshTokenRepositoryBase {
}
