package com.questglobal.SmartHome.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Devices")
public class Device {
    @Id
    @Field("_id")
    private ObjectId id;
    @Field("Name")
    private String deviceName;
    @Field("HexId")
    private String hexId;
    @Field("Power")
    private Integer power;
    @Field("Device")
    private String device;
    @Field("Ct")
    private Integer ct;
    @Field("Dimmer")
    private Integer dimmer;
    @Field("ModelId")
    private String modelId;
    @Field("Manufacturer")
    private String manufacturer;
    @Field("Reachable")
    private boolean reachable;
    @Field("LastSeen")
    private Integer lastSeen;
    @Field("LastSeenEpoch")

    private Integer lastSeenEpoch;
    @Field("LinkQuality")
    private Integer linkQuality;
    @Field("Endpoints")
    private ArrayList<Integer> endpoints;
    @Field("Hue")
    private int hue;
    @Field("Sat")
    private int sat;
    @Field("Topic")
    private String topic;
    @Field("RestartReason")
    private String restartReason;
    @Field("Uptime")
    private String uptime;
    @Field("StartupUTC")
    private Date startupUTC;
    @Field("WifiSSId")
    private ArrayList<String> wifiSSId;
    @Field("TelePeriod")
    private int telePeriod;
    @Field("HostName")
    private String hostName;
    @Field("IpAddress")
    private String ipAddress;
    @Field("Gateway")
    private String gateway;
    @Field("SubnetMask")
    private String subnetMask;
    @Field("Temperature")
    private String temperature;
    @Field("TemperatureUnit")
    private String temperatureUnit;
    @Field("Power1")
    private String power1;
    @Field("Power2")
    private String power2;
    @Field("LedState")
    private int ledState;
    @Field("BatteryPercentage")
    private int batteryPercentage;
    @Field("ZoneStatus")
    private int zoneStatus;
    @Field("Occupancy")
    private int occupancy;


    public Device(String deviceName, String hexId) {
        this.id = new ObjectId();
        this.deviceName = deviceName;
        this.hexId = hexId;

    }


}
