package com.questglobal.SmartHome.controllers;

import com.questglobal.SmartHome.dto.DeviceDto;
import com.questglobal.SmartHome.models.Device;
import com.questglobal.SmartHome.repositories.DeviceRepository;
import com.questglobal.SmartHome.response.MessageResponse;
import com.questglobal.SmartHome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping(produces = {"application/json"})
    public Device getDevice(String id) {
        return deviceService.findDeviceById(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable("id") String id) {
        if (deviceService.findDeviceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        deviceService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Device name updated successfully"));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateDevice(@PathVariable("id") String id, @RequestBody DeviceDto dto) {
        if (deviceService.findDeviceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        deviceService.update(id, dto);
        return ResponseEntity.ok(new MessageResponse("Device name updated successfully"));
    }

}
