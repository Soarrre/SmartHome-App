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
@Document("DeviceTypes")
public class DeviceType {
    @Field("DeviceHexName")
    public String deviceHexName;
    @Id
    @Field("_id")
    private ObjectId id;
    @Field("Name")
    private String deviceTypeName;
    @Field("mqttTopics")
    private ArrayList<String> mqttTopics;

    public DeviceType(String deviceTypeName, String deviceHexName, ArrayList<String> mqttTopics) {
        this.id = new ObjectId();
        this.deviceTypeName = deviceTypeName;
        this.deviceHexName = deviceHexName;
        this.mqttTopics = new ArrayList<String>(mqttTopics);
    }


}
