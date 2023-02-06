package com.questglobal.SmartHome.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttPublishModel {
    private String topic;
    private String payload;
    private int qos;


}