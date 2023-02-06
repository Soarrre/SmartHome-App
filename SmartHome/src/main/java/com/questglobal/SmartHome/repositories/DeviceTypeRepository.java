package com.questglobal.SmartHome.repositories;


import com.questglobal.SmartHome.models.DeviceType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTypeRepository extends MongoRepository<DeviceType, String> {
    DeviceType findDeviceById(String id);
    Optional<DeviceType> findDeviceTypeByDeviceHexName(String deviceHexName);


}
