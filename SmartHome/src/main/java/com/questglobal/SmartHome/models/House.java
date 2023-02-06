package com.questglobal.SmartHome.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@Document("Houses")
public class House {

    @Id
    @Field("_id")
    private ObjectId id;
    @Field("Name")
    private String name;

    @Field("Rooms")
    private ArrayList<ObjectId> rooms;

    @Field("Devices")
    private ArrayList<ObjectId> devices;


    public House(String name, ArrayList<ObjectId> devices, ArrayList<ObjectId> rooms) {
        this.name = name;
        this.devices = devices;
        this.rooms = rooms;
    }

    public House(String name) {
        this.name = name;
        this.devices = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public void addRoom(ObjectId room) {
        this.rooms.add(room);
    }

}
