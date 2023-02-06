package com.questglobal.SmartHome.services;

import com.questglobal.SmartHome.config.MqttConfig;
import com.questglobal.SmartHome.dto.DeviceDto;
import com.questglobal.SmartHome.models.Device;
import com.questglobal.SmartHome.repositories.DeviceRepository;
import com.questglobal.SmartHome.repositories.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private final MqttConfig mqttConfig;
    private final DeviceTypeRepository deviceTypeRepository;
    private final DeviceRepository deviceRepository;
    @Autowired
    DeviceService(MqttConfig mqttConfig, DeviceRepository deviceRepository, DeviceTypeRepository deviceTypeRepository) {
        this.mqttConfig = mqttConfig;
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public Device saveDevice(Device device) {

        return deviceRepository.save(device);
    }

    public Device findDeviceById(String id) {
        return deviceRepository.findDeviceTypeById(id);
    }

    public Boolean delete(String id) {
        Device dbDevice = deviceRepository.findDeviceTypeById(id);
        if (dbDevice == null) {
            throw new IllegalStateException(" device with id " + id + " does not exist ");
        }
        deviceRepository.deleteById(id);
        return true;
    }

    public Boolean update(String id, DeviceDto dto) {
        Device dbDevice = deviceRepository.findDeviceTypeById(id);
        if (dbDevice.getId() == null) throw new IllegalStateException("Device does not exist");
        if (!dto.deviceName.isBlank()) dbDevice.setDeviceName(dto.deviceName);
        deviceRepository.save(dbDevice);
        return true;
    }


}
