package com.questglobal.SmartHome.controllers;

import com.questglobal.SmartHome.dto.RoomDto;
import com.questglobal.SmartHome.models.Room;
import com.questglobal.SmartHome.response.MessageResponse;
import com.questglobal.SmartHome.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(produces = {"application/json"})
    public Room getRoom(String id) {
        return roomService.findRoomById(id);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<?> registerNewRoom(@RequestBody RoomDto dto) {
        roomService.insertNew(dto);
        return ResponseEntity.ok(new MessageResponse("Room registered successfully"));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") String id) {
        roomService.delete(id);
        if (roomService.findRoomById(id) == null) {
            return ResponseEntity
                    .notFound().build();
        }
        roomService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Room deleted successfully"));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateRoom(@PathVariable("id") String id, @RequestBody RoomDto request) {

        roomService.update(id, request);
        return ResponseEntity.ok(new MessageResponse("Room updated successfully"));
    }
}
