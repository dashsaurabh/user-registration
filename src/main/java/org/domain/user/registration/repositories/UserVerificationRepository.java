package org.domain.user.registration.repositories;

import org.domain.user.registration.entities.UserVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserVerificationRepository extends CrudRepository<UserVerification, UUID> {
}
