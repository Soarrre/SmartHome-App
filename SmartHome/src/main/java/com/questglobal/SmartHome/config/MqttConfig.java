package com.questglobal.SmartHome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questglobal.SmartHome.models.Device;
import com.questglobal.SmartHome.repositories.DeviceRepository;
import com.questglobal.SmartHome.services.DeviceService;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final String MQTT_PUBLISHER_ID = "smarthome";
    private static final String MQTT_SERVER_ADDRES = "tcp://0.0.0.0:1883";
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    private static IMqttClient instance;
    @Autowired
    private final DeviceRepository deviceRepository;

    private String subscribeTopic;

    public MqttConfig(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.subscribeTopic = subscribeTopic;
    }


    @Bean
    public IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_SERVER_ADDRES, MQTT_PUBLISHER_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
                subscribeToMqtt();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private void subscribeToMqtt() {
        try {
            instance.subscribe(subscribeTopic, (s, mqttMessage) -> {
                String arrivedMessage = new String(mqttMessage.getPayload());
                logger.info("Message received : " + arrivedMessage);
                handleMessageArrived(arrivedMessage);
            });
        } catch (MqttException e) {
            logger.error("Error occurred while subscribing to topic {}", subscribeTopic, e);
        }
    }

    private Device handleMessageArrived(String arrivedMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Device device = objectMapper.readValue(arrivedMessage, Device.class);

            JSONObject jsonObject = new JSONObject(arrivedMessage);

            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("ZbStatus3"));

            device.setHexId(((JSONObject) jsonArray.get(0)).getString("Device"));

            return deviceRepository.save(device);

        } catch (Exception e) {
            throw new RuntimeException("Entity not saved");
        }
    }
}