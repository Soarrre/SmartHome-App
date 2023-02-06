package com.questglobal.SmartHome.services;

import com.questglobal.SmartHome.config.MqttConfig;
import com.questglobal.SmartHome.services.impl.IWebSocketService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketService implements IWebSocketService {

    private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private final MqttConfig mqttConfig;


    public WebSocketService(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    public void subscribeToTopic(WebSocketSession session, String subscribeTopic) {


        try {
            mqttConfig.getInstance().subscribe(subscribeTopic, (s, mqttMessage) -> {
                String arrivedMessage = new String(mqttMessage.getPayload());
                logger.info("The received message is: " + arrivedMessage);
                session.sendMessage(new TextMessage(arrivedMessage));
            });
        } catch (MqttException e) {
            logger.error("Error : Failed to subscribe to topic", subscribeTopic, e);
        }


    }
}