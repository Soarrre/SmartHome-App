package com.questglobal.SmartHome.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class DeviceTypeDto implements Serializable {
    public String deviceTypeName;
    public String deviceHexName;
    public ArrayList<String> mqttTopics;
}
