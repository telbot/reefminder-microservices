package org.reefminder.microservice.auth.repositories;

import org.reefminder.microservice.auth.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryBase {

}
