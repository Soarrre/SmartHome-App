package com.questglobal.SmartHome.repositories;

import com.questglobal.SmartHome.models.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {
    House findHouseById(String id);
    Optional<House> findHouseByName(String Name);
}
