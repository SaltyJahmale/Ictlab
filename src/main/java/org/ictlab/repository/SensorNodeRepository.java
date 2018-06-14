package org.ictlab.repository;

import org.ictlab.domain.node.SensorNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorNodeRepository extends JpaRepository<SensorNode, Long> {
    SensorNode findByName(String name);
}
