package com.questglobal.SmartHome.controllers;

import com.questglobal.SmartHome.dto.HouseDto;
import com.questglobal.SmartHome.models.House;
import com.questglobal.SmartHome.response.MessageResponse;
import com.questglobal.SmartHome.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/houses")

public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping(produces = {"application/json"})
    public House getHouse(String id) {
        return houseService.findHouseById(id);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<?> registerNewHouse(@RequestBody String houseName) {
        houseService.insertNew(houseName);
        return ResponseEntity.ok(new MessageResponse("House registered successfully"));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable("id") String id) {

        if (houseService.findHouseById(id) == null) {
            return ResponseEntity
                    .notFound().build();
        }
        houseService.delete(id);
        return ResponseEntity.ok(new MessageResponse("House deleted successfully"));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateHouse(@PathVariable("id") String id, @RequestBody HouseDto request) {

        if (houseService.findHouseById(id) == null) {
            return ResponseEntity
                    .notFound().build();
        }
        houseService.update(id, request);
        return ResponseEntity.ok(new MessageResponse("House name updated successfully"));
    }
}
