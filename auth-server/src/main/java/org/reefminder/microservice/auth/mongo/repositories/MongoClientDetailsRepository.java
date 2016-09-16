package org.reefminder.microservice.auth.mongo.repositories;

import org.reefminder.microservice.auth.mongo.domain.MongoClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MongoClientDetailsRepository extends MongoRepository<MongoClientDetails, String>, MongoClientDetailsRepositoryBase {
}
