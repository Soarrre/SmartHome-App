package com.questglobal.SmartHome.repositories;

import com.questglobal.SmartHome.models.ERole;
import com.questglobal.SmartHome.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
