package org.ictlab.rest;

import org.ictlab.Service.SensorDataService;
import org.ictlab.domain.sensor.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensordata")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping(value = "/{id}")
    public Optional<SensorData> getSensorData(@PathVariable Long id) {
        return sensorDataService.findById(id);
    }

    @GetMapping
    public List<SensorData> getAllSensorData() {
        return sensorDataService.findAll();
    }

    @PostMapping
    public void createNodeData(@RequestBody SensorData sensorData) {
        sensorDataService.createOrUpdate(sensorData);
    }

}
