package com.questglobal.SmartHome.controllers;


import com.questglobal.SmartHome.dto.DeviceDto;
import com.questglobal.SmartHome.dto.DeviceTypeDto;
import com.questglobal.SmartHome.models.DeviceType;
import com.questglobal.SmartHome.response.MessageResponse;
import com.questglobal.SmartHome.services.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/devicetype")
public class DeviceTypeController {
    private final DeviceTypeService deviceTypeService;

    @Autowired
    public DeviceTypeController(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping(produces = {"application/json"})
    public DeviceType getDeviceType(String id) {
        return deviceTypeService.findDeviceTypeById(id);
    }

    @PostMapping()
    public ResponseEntity<?> registerNewDeviceTypeIntoSystem(@RequestParam String deviceHexName, String deviceTypeName, String deviceName, @RequestBody DeviceTypeDto dto, DeviceDto dto2, @RequestParam(required = false) String payload) {
        deviceTypeService.AddDeviceIntoSystem(deviceHexName, deviceName, deviceTypeName, dto, dto2, payload);
        return ResponseEntity.ok(new MessageResponse("Device type registered successfully"));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteDeviceType(@PathVariable("id") String id) {
        if (deviceTypeService.findDeviceTypeById(id) == null) {
            return ResponseEntity
                    .notFound().build();
        }
        deviceTypeService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Device type deleted successfully"));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateDeviceType(@PathVariable("id") String id, @RequestBody DeviceTypeDto dto) {
        if (deviceTypeService.findDeviceTypeById(id) == null) {
            return ResponseEntity
                    .notFound().build();
        }
        deviceTypeService.update(id, dto);
        return ResponseEntity.ok(new MessageResponse("Device type updated successfully"));
    }

}
