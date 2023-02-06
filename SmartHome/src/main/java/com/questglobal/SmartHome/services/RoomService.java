package com.questglobal.SmartHome.services;

import com.questglobal.SmartHome.dto.RoomDto;
import com.questglobal.SmartHome.models.Room;
import com.questglobal.SmartHome.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;


    @Autowired
    RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findRoomById(String id) {
        return roomRepository.findRoomById(id);
    }

    public String insertNew(RoomDto dto) {
        Optional<Room> dbRoom = roomRepository.findRoomByName(dto.name);
        if (dbRoom.isPresent()) {
            throw new IllegalStateException("Room with the same name already exists");
        }

        Room room = new Room(dto.houseId, dto.name);
        roomRepository.save(room);
        return room.getId().toHexString();
    }

    public Boolean delete(String id) {
        Room dbRoom = roomRepository.findRoomById(id);
        if (dbRoom == null) {
            throw new IllegalStateException(" House with id " + id + " does not exist ");
        }
        roomRepository.deleteById(id);
        return true;
    }

    public Boolean update(String id, RoomDto dto) {
        Room dbRoom = roomRepository.findRoomById(id);
        if (dbRoom == null) {
            insertNew(dto);
        } else {
            if (!dto.name.isBlank()) dbRoom.setName(dto.name);

            roomRepository.save(dbRoom);
        }
        return true;
    }
}
