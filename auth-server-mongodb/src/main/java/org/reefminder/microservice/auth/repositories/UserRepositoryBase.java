package org.reefminder.microservice.auth.repositories;

public interface UserRepositoryBase {

    boolean changePassword(String oldPassword, String newPassword, String username);

}
