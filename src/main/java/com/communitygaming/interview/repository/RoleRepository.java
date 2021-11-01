package com.communitygaming.interview.repository;

import java.util.Optional;

import com.communitygaming.interview.model.ERole;
import com.communitygaming.interview.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
