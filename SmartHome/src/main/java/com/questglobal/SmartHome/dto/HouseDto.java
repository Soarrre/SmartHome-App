package com.questglobal.SmartHome.dto;

import com.questglobal.SmartHome.models.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class HouseDto implements Serializable {

    public String name;
    public ArrayList<ObjectId> room;
    public ArrayList<Device> device;


}
