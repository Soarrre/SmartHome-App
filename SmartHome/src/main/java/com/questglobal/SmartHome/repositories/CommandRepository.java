package com.questglobal.SmartHome.repositories;

import com.questglobal.SmartHome.models.Command;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends MongoRepository<Command, ObjectId> {
    Optional<Command> findCommandByName(String name);
}
