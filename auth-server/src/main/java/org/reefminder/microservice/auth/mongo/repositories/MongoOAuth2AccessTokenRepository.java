package org.reefminder.microservice.auth.mongo.repositories;

import org.reefminder.microservice.auth.mongo.domain.MongoOAuth2AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOAuth2AccessTokenRepository extends MongoRepository<MongoOAuth2AccessToken, String>, MongoOAuth2AccessTokenRepositoryBase {

}
