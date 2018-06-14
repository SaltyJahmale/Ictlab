package org.ictlab.domain.sensor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "SENSORDATA")
public class SensorData {

    @Id
    @Column( name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensordata_seq")
    @SequenceGenerator(name = "sensordata_seq", sequenceName = "sensordata_seq", allocationSize = 1)
    private Long id;

    @Column( name = "NAME")
    private String name;

    @Column( name = "SENSORLOCATION")
    private String sensorLocation;

    @Column(name = "SENSORINPUTDATA")
    private Double sensorInputData;

    @Column(name = "SENSORDATAENUM")
    @Enumerated(EnumType.STRING)
    private SensorDataEnum sensorDataEnum;

    @Column(name = "SENSORMEASUREMENT")
    @Enumerated(EnumType.STRING)
    private SensorMeasurement sensorMeasurement;

    @Column( name = "LOCALDATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public Double getSensorInputData() {
        return sensorInputData;
    }

    public void setSensorInputData(Double sensorInputData) {
        this.sensorInputData = sensorInputData;
    }

    public SensorDataEnum getSensorDataEnum() {
        return sensorDataEnum;
    }

    public void setSensorDataEnum(SensorDataEnum sensorDataEnum) {
        this.sensorDataEnum = sensorDataEnum;
    }

    public SensorMeasurement getSensorMeasurement() {
        return sensorMeasurement;
    }

    public void setSensorMeasurement(SensorMeasurement sensorMeasurement) {
        this.sensorMeasurement = sensorMeasurement;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
