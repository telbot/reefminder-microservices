package org.reefminder.microservice.auth.mongo.repositories;

import org.reefminder.microservice.auth.mongo.domain.MongoApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoApprovalRepository extends MongoRepository<MongoApproval, String>, MongoApprovalRepositoryBase {
}
