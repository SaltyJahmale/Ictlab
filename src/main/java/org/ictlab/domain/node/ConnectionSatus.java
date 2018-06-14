package org.ictlab.domain.node;

import java.io.Serializable;

public enum ConnectionSatus implements Serializable {
    CONNECTED("CONNECTED"), DISCONNECTED("DISCONNECTED"), UNKNOWN("UNKNOWN");

    private final String value;

    ConnectionSatus(String value) {
        this.value = value;
    }

    public static ConnectionSatus fromValue(String value) {
        if (value != null) {
            for (ConnectionSatus data : values()) {
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

    public static ConnectionSatus getDefault() {
        return UNKNOWN;
    }
}
