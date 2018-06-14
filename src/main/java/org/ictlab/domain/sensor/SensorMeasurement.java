package org.ictlab.domain.sensor;

public enum SensorMeasurement {
    CELSIUS("CELSIUS"), KELVIN("KELVIN"), CENTIMETER("CENTIMETER"), INCH("INCH"), UNKNOWN("UNKNOWN");

    private final String value;

    SensorMeasurement(String value) {
        this.value = value;
    }

    public static SensorMeasurement fromValue(String value) {
        if (value != null) {
            for (SensorMeasurement data : values()) {
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

    public static SensorMeasurement getDefault() {
        return UNKNOWN;
    }
}
