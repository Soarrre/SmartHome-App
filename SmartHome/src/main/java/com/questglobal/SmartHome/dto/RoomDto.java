package com.questglobal.SmartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class RoomDto implements Serializable {

    public String name;
    public ObjectId houseId;
}

