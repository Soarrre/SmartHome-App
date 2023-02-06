package com.questglobal.SmartHome.services;


import com.questglobal.SmartHome.config.MqttConfig;
import com.questglobal.SmartHome.dto.DeviceDto;
import com.questglobal.SmartHome.dto.DeviceTypeDto;
import com.questglobal.SmartHome.models.Device;
import com.questglobal.SmartHome.models.DeviceType;
import com.questglobal.SmartHome.models.MqttPublishModel;
import com.questglobal.SmartHome.repositories.DeviceRepository;
import com.questglobal.SmartHome.repositories.DeviceTypeRepository;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceTypeService {
    private final DeviceTypeRepository deviceTypeRepository;
    private final DeviceRepository deviceRepository;

    private final MqttConfig mqttConfig;

    @Autowired
    DeviceTypeService(DeviceTypeRepository deviceTypeRepository, DeviceRepository deviceRepository, MqttConfig mqttConfig) {
        this.deviceTypeRepository = deviceTypeRepository;
        this.deviceRepository = deviceRepository;
        this.mqttConfig = mqttConfig;
    }

    public DeviceType findDeviceTypeById(String id) {
        return deviceTypeRepository.findDeviceById(id);
    }

    public Boolean delete(String id) {
        DeviceType dbDeviceType = deviceTypeRepository.findDeviceById(id);
        if (dbDeviceType == null) {
            throw new IllegalStateException(" device with id " + id + " does not exist ");
        }
        deviceTypeRepository.deleteById(id);
        return true;
    }

    public Boolean update(String id, DeviceTypeDto dto) {
        DeviceType dbDeviceType = deviceTypeRepository.findDeviceById(id);
        if (!dto.deviceTypeName.isBlank()) dbDeviceType.setDeviceTypeName(dto.deviceTypeName);
        if (!dto.deviceHexName.isBlank()) dbDeviceType.setDeviceHexName(dto.deviceHexName);

        deviceTypeRepository.save(dbDeviceType);

        return true;
    }

    public Optional<DeviceType> findDeviceByDeviceHexName(String deviceHexName) {
        return deviceTypeRepository.findDeviceTypeByDeviceHexName(deviceHexName);
    }

    public Optional<Device> findDeviceByName(String deviceName) {
        return deviceRepository.findDeviceByDeviceName(deviceName);
    }

    public String AddDeviceIntoSystem(String deviceHexName, String deviceName, String deviceTypeName, DeviceTypeDto dto, DeviceDto dto2, String payload) {
        Optional<DeviceType> dbDeviceType = findDeviceByDeviceHexName(deviceHexName);
        Optional<Device> dbDevice = findDeviceByName(deviceName);

        if (dbDeviceType == null) {
            throw new IllegalStateException("Device HexName does not exist");
        }

        if (dbDevice.isPresent()) {
            throw new IllegalStateException("Name of the device already exists");
        }

        DeviceType deviceType = new DeviceType(deviceTypeName, deviceHexName, dto.mqttTopics);
        deviceTypeRepository.save(deviceType);
        Device device = new Device(deviceName, dto2.HexId);
        deviceRepository.save(device);
        MqttPublishModel publishModel = new MqttPublishModel();

        try {
            publishModel.setPayload(payload);
            publishModel.setTopic("stat/#");
            publishModel.setQos(2);

            MqttMessage mqttMessage = new MqttMessage(publishModel.getPayload().getBytes());
            mqttConfig.getInstance().publish(publishModel.getTopic(), mqttMessage);
        } catch (Exception exception) {
            throw new IllegalStateException("The device was not added due to connectivity problems");
        }
        return String.valueOf(deviceRepository.findDeviceByHexId(deviceHexName));

    }
}

