package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.MongoClientDetails;

public interface MongoClientDetailsRepositoryBase {
    boolean deleteByClientId(String clientId);

    boolean update(MongoClientDetails mongoClientDetails);

    boolean updateClientSecret(String clientId, String newSecret);

    MongoClientDetails findByClientId(String clientId) throws IllegalArgumentException;
}
