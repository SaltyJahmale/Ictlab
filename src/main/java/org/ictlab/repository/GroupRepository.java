package org.ictlab.repository;

import org.ictlab.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByGroupNameContaining(@NotNull String groupName);
    Group findByGroupName(@NotNull String groupName);
}
