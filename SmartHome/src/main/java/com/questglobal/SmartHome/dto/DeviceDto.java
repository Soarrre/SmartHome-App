package com.questglobal.SmartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class DeviceDto implements Serializable {
    public String deviceName;
    public String HexId;

    public String topic;
}
