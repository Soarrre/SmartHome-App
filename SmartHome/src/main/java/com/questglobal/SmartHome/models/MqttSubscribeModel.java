package com.questglobal.SmartHome.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttSubscribeModel {

    private String message;
    private Integer id;


}