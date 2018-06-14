package org.ictlab.rest;

import org.ictlab.Service.SensorNodeService;
import org.ictlab.domain.node.SensorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensornodes")
public class SensorNodeController {

    private final SensorNodeService sensorNodeService;

    @Autowired
    public SensorNodeController(@RequestBody SensorNodeService sensorNodeService) {
        this.sensorNodeService = sensorNodeService;
    }

    @GetMapping(value = "/{name}")
    public SensorNode getSensorNode(@PathVariable String name) {
        return sensorNodeService.findByName(name);
    }

    @GetMapping(value = "")
    public List<SensorNode> getAllSensorNodes() {
        return sensorNodeService.findAll();
    }

    @PostMapping(value = "")
    public void createNode(@RequestBody SensorNode sensorNode) {
        sensorNodeService.createOrUpdate(sensorNode);
    }

    @PutMapping
    public void updateNode(@RequestBody SensorNode sensorNode) {
        sensorNodeService.createOrUpdate(sensorNode);
    }

    @DeleteMapping(value = "/{name}")
    public void deleteNode(@PathVariable String name) {
        SensorNode sensorNode = sensorNodeService.findByName(name);
        sensorNodeService.removeNode(sensorNode);
    }
}
