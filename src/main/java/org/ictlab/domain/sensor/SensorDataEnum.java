package org.ictlab.domain.sensor;

import java.io.Serializable;

public enum SensorDataEnum implements Serializable {
    TEMP("TEMP"), DENSITY("DENSITY"), PROXIMITY("PROXIMITY"), LIGHT("LIGHT"), PRESSURE("PRESSURE"), UNKNOWN("UNKNOWN");

    private final String value;

    SensorDataEnum(String value) {
        this.value = value;
    }

    public static SensorDataEnum fromValue(String value) {
        if (value != null) {
            for (SensorDataEnum data : values()) {
                if (data.value.equals(value)) {
                    return data;
                }
            }
        }
        // you may return a default value
        return getDefault();
        // or throw an exception
        // throw new IllegalArgumentException("Invalid color: " + value);
    }

    public String toValue() {
        return value;
    }

    public static SensorDataEnum getDefault() {
        return UNKNOWN;
    }
}

