package org.ictlab.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.ictlab.Service.SensorDataService;
import org.ictlab.Service.SensorNodeService;
import org.ictlab.domain.sensor.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class MqttConfig implements MqttCallback {

    private Logger log = LoggerFactory.getLogger(MqttConfig.class);
    private MqttClient client;
    private final SensorDataService sensorDataService;
    private ObjectMapper mapper = new ObjectMapper();
    private final SensorNodeService sensorNodeService;

    @Value(value = "${mqtt.serverip}")
    private String serverIp;

    @Value(value = "${mqtt.serverport}")
    private String serverPort;


    @Autowired
    public MqttConfig(SensorDataService sensorDataService, SensorNodeService sensorNodeService) {
        this.sensorDataService = sensorDataService;
        this.sensorNodeService = sensorNodeService;
    }

    @Bean
    public boolean connect() {
        String connectionUrl = String.format("%s:%s", this.serverIp, this.serverPort);
        try {
            this.client = new MqttClient(connectionUrl, UUID.randomUUID().toString());
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            client.connect(mqttConnectOptions);
            client.setCallback(this);
            log.info(String.format("The client is connected to %s", connectionUrl));
        } catch (MqttException e) {
            log.info(String.format("======%s=======", e.getMessage()));
        }

        return client.isConnected();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.info("Client is down");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        try {
            String payload = new String(mqttMessage.getPayload());
            System.out.println(payload);
            SensorData sensorData = mapper.readValue(payload, SensorData.class);
            LocalDateTime localDateTime = LocalDateTime.now();
            sensorData.setLocalDateTime(localDateTime);
            sensorDataService.createOrUpdate(sensorData);
            log.info(String.format("====== Received: %s =====", mqttMessage.toString()));
        } catch (Exception e) {
            log.info(String.format("Something went wrong saving incoming mqtt messages: %s", e));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Only for publishing messages
    }

    public void subscribe(String topic) throws MqttException {
        if(!this.client.isConnected()) {
            this.connect();
            log.info("==== Mqtt connecting to Client ====");
        } else {
            client.subscribe(topic);
            log.info(String.format("==== Mqtt subscribed to %s ====", topic));
        }
    }

    public void unsubscribe(String topic) throws MqttException {
        if(!client.isConnected()) {
            this.connect();
            log.info("==== Mqtt connecting first ====");
        } else {
            client.unsubscribe(topic);
            log.info(String.format("==== Mqtt unsubscribe from %s ====", topic));

        }
    }

}
