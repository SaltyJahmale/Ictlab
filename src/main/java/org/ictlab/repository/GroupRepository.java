package org.ictlab.repository;

import org.ictlab.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT * FROM class ", nativeQuery = true)
    List<Group> getAllClasses();

    Group findByGroupName(@NotNull String groupName);
}
