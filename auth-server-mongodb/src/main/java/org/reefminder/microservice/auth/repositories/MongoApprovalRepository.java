package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoApprovalRepository extends MongoRepository<MongoApproval, String>, MongoApprovalRepositoryBase {
}
