package org.ictlab.domain.node;

import org.ictlab.domain.sensor.SensorDataEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SENSORNODE")
public class SensorNode implements Serializable {

    @Id
    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "IP")
    private String ip;

    @Column(name = "PORT")
    private String port;

    @Column(name = "TOPIC", unique = true)
    private String topic;

    @Column(name = "SENSORNODESTATUS")
    private String sensorNodeStatus = "OK";

    @Column(name = "SENSORDATAENUM")
    private String sensorDataEnum;

    @Column(name = "CONNECTIONSTATUS")
    private String connectionStatus = "DISCONNECTED";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public SensorNodeStatus getSensorNodeStatus() {
        return SensorNodeStatus.fromValue(sensorNodeStatus);
    }

    public void setSensorNodeStatus(SensorNodeStatus sensorNodeStatus) {
        this.sensorNodeStatus = sensorNodeStatus.toValue();
    }

    public SensorDataEnum getSensorDataEnum() {
        return SensorDataEnum.fromValue(sensorDataEnum);
    }

    public void setSensorDataEnum(SensorDataEnum sensorDataEnum) {
        this.sensorDataEnum = sensorDataEnum.toValue();
    }

    @Transient
    public ConnectionSatus getConnectionStatus() {
        return ConnectionSatus.fromValue(connectionStatus);
    }

    public void setConnectionStatus(ConnectionSatus connectionStatus) {
        this.connectionStatus = connectionStatus.toValue();
    }
}
