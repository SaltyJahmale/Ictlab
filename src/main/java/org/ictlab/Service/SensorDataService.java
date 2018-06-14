package org.ictlab.Service;

import org.ictlab.domain.sensor.SensorData;
import org.ictlab.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;


    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public void createOrUpdate(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }

    public Optional<SensorData> findById(Long id) {
        return sensorDataRepository.findById(id);
    }

    public List<SensorData> findAll() {
        return sensorDataRepository.findAll();
    }

}
