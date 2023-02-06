package com.questglobal.SmartHome.repositories;

import com.questglobal.SmartHome.models.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    Device findDeviceTypeById(String id);
    Optional<Device> findDeviceByDeviceName(String deviceName);
    Optional<Device> findDeviceByHexId(String deviceHexName);
}
