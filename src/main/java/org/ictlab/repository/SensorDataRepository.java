package org.ictlab.repository;

import org.ictlab.domain.sensor.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
