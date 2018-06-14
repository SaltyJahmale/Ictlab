package org.ictlab.rest;

import org.ictlab.Service.SensorNodeService;
import org.ictlab.config.MqttConfig;
import org.ictlab.domain.node.SensorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mqtt")
public class MqttController {

    private Logger log = LoggerFactory.getLogger(MqttController.class);
    private final MqttConfig sub;
    private final SensorNodeService sensorNodeService;

    @Autowired
    public MqttController(MqttConfig sub, SensorNodeService sensorNodeService) {
        this.sub = sub;
        this.sensorNodeService = sensorNodeService;
    }

    @PostMapping("/node")
    public void createNode(@RequestBody SensorNode sensorNode) {
        try {
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PutMapping("/subscribe")
    public void subscribeToTopic(@RequestBody SensorNode sensorNode) {
        try {
            sub.subscribe(sensorNode.getTopic());
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PutMapping("/unsubscribe")
    public void unsubscribeFromTopic(@RequestBody SensorNode sensorNode) {
        try {
            sub.unsubscribe(sensorNode.getTopic());
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping("/node")
    public SensorNode getNodeById(@RequestHeader String name ) {
        return sensorNodeService.findByName(name);
    }

    @GetMapping("/nodes")
    public List<SensorNode> getAllNodes() {
        return sensorNodeService.findAll();
    }

}
