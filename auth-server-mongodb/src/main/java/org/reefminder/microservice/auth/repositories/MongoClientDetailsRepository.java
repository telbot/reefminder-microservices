package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoClientDetailsRepository extends MongoRepository<MongoClientDetails, String>, MongoClientDetailsRepositoryBase {
}
