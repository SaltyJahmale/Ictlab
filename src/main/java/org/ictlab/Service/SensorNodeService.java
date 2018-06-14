package org.ictlab.Service;

import org.ictlab.domain.node.SensorNode;
import org.ictlab.repository.SensorNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorNodeService {

    private final SensorNodeRepository sensorNodeRepository;

    @Autowired
    public SensorNodeService(SensorNodeRepository sensorNodeRepository) {
        this.sensorNodeRepository = sensorNodeRepository;
    }

    public void createOrUpdate(SensorNode sensorNode) {
        sensorNodeRepository.save(sensorNode);
    }

    public SensorNode findByName(String name) {
        return sensorNodeRepository.findByName(name);
    }

    public List<SensorNode> findAll() {
        return sensorNodeRepository.findAll();
    }

    public void removeNode(SensorNode sensorNode) {
        sensorNodeRepository.delete(sensorNode);
    }

}
