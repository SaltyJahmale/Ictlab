package org.ictlab.domain.node;

import java.io.Serializable;

public enum SensorNodeStatus implements Serializable {
    OK("OK"), INTERMITTENT_FAILURE("INTERMITTENT_FAILURE"), DOWN("DOWN"), UNKNOWN("UNKNOWN");

    private final String value;

    SensorNodeStatus(String value) {
        this.value = value;
    }

    public static SensorNodeStatus fromValue(String value) {
        if (value != null) {
            for (SensorNodeStatus data : values()) {
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

    public static SensorNodeStatus getDefault() {
        return UNKNOWN;
    }
}
