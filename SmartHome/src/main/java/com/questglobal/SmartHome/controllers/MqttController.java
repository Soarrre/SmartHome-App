package com.questglobal.SmartHome.controllers;

import com.questglobal.SmartHome.config.MqttConfig;
import com.questglobal.SmartHome.exceptions.ExceptionMessages;
import com.questglobal.SmartHome.models.MqttPublishModel;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {

    private final MqttConfig mqttConfig;

    public MqttController(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    @PostMapping("publish")
    public void publishMessage(@RequestParam String payload, @RequestBody @Valid MqttPublishModel messagePublishModel, BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {
        if (bindingResult.hasErrors()) {
            throw new MqttException(Integer.parseInt(ExceptionMessages.SOME_PARAMETERS_INVALID));
        }

        messagePublishModel.setPayload(payload);
        messagePublishModel.setTopic("cmnd/nspanelswitch4/POWER1");

        MqttMessage mqttMessage = new MqttMessage(messagePublishModel.getPayload().getBytes());
        mqttConfig.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
    }


}