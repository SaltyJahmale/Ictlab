package org.ictlab.domain;

import java.io.Serializable;

public enum State implements Serializable {
    CANCELLED("CANCELLED"), NEWS("NEWS"), REMOVED("REMOVED"), CHANGED("CHANGED"), UNKNOWN("UNKNOWN");

    private final String value;

    State (String value) {
        this.value = value;
    }

    public static State fromValue(String value) {
        if (value != null) {
            for (State data : values()) {
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

    public static State getDefault() {
        return UNKNOWN;
    }
}
