package org.ictlab.Service;

import org.ictlab.domain.Group;
import org.ictlab.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;


    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * @param groupName
     * @return Group
     */
    public Group getGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    /**
     * @return List<Group>
     */
    public List<Group> getAllGroups() {
        return groupRepository.getAllClasses();
    }

    /**
     * @return List<Group>
     */
    public List<Group> getAllGroupsContaining() {
        return groupRepository.findAll();
    }
}
