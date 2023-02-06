package com.questglobal.SmartHome.services;

import com.questglobal.SmartHome.dto.HouseDto;
import com.questglobal.SmartHome.models.House;
import com.questglobal.SmartHome.models.Room;
import com.questglobal.SmartHome.repositories.HouseRepository;
import com.questglobal.SmartHome.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    @Autowired
    HouseService(HouseRepository houseRepository, RoomRepository roomRepository) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    public House findHouseById(String id) {
        return houseRepository.findHouseById(id);
    }

    public String insertNew(String houseName) {
        Optional<House> dbHouse = houseRepository.findHouseByName(houseName);
        if (dbHouse.isPresent()) {
            throw new IllegalStateException("name already exists");
        }

        House house = houseRepository.save(new House(houseName));
        house.addRoom(roomRepository.save(new Room(house.getId(), "Kitchen")).getId());
        house.addRoom(roomRepository.save(new Room(house.getId(), "LivingRoom")).getId());
        house.addRoom(roomRepository.save(new Room(house.getId(), "Bedroom")).getId());

        houseRepository.save(house);
        return house.getId().toString();
    }

    public Boolean delete(String id) {
        House dbHouse = houseRepository.findHouseById(id);
        if (dbHouse == null) {
            throw new IllegalStateException(" House with id " + id + " does not exist ");
        }
        houseRepository.deleteById(id);
        return true;
    }

    public Boolean update(String id, HouseDto dto) {
        House dbHouse = houseRepository.findHouseById(id);
        if (!dto.name.isBlank()) {
            dbHouse.setName(dto.name);

            houseRepository.save(dbHouse);
        }
        return true;
    }
}
