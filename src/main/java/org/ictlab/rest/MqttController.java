package org.ictlab.rest;

import org.ictlab.Service.SensorNodeService;
import org.ictlab.config.MqttConfig;
import org.ictlab.domain.node.ConnectionSatus;
import org.ictlab.domain.node.SensorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mqtt")
public class MqttController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final MqttConfig sub;
    private final SensorNodeService sensorNodeService;

    @Autowired
    public MqttController(MqttConfig sub, SensorNodeService sensorNodeService) {
        this.sub = sub;
        this.sensorNodeService = sensorNodeService;
    }

    /**
     * @param sensorNode
     */
    @PostMapping("/node")
    public void createNode(@RequestBody SensorNode sensorNode) {
        try {
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param sensorName
     */
    @PutMapping("/sub/{name}")
    public void subscribeToTopic(@PathVariable("name") String sensorName) {
        try {
            SensorNode sensorNode = sensorNodeService.findByName(sensorName);
            sub.subscribe(sensorNode.getTopic());
            sensorNode.setConnectionStatus(ConnectionSatus.CONNECTED);
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param sensorName
     */
    @PutMapping("/unsub/{name}")
    public void unsubscribeFromTopic(@PathVariable("name") String sensorName) {
        try {
            SensorNode sensorNode = sensorNodeService.findByName(sensorName);
            sub.unsubscribe(sensorNode.getTopic());
            sensorNode.setConnectionStatus(ConnectionSatus.DISCONNECTED);
            sensorNodeService.createOrUpdate(sensorNode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param name
     * @return SensorNode
     */
    @GetMapping("/node")
    public SensorNode getNodeById(@RequestHeader String name ) {
        return sensorNodeService.findByName(name);
    }

    /**
     * @return List<SensorNode>
     */
    @GetMapping("/nodes")
    public List<SensorNode> getAllNodes() {
        return sensorNodeService.findAll();
    }

}
