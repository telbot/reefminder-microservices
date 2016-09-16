package org.reefminder.microservice.auth.mongo.repositories;

import org.reefminder.microservice.auth.mongo.domain.MongoOAuth2ClientToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOAuth2ClientTokenRepository extends MongoRepository<MongoOAuth2ClientToken, String>, MongoOAuth2ClientTokenRepositoryBase {
}
