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

    public boolean groupExistByName(String groupName) {
        return groupRepository.findByGroupName(groupName) != null;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<Group> getAllGroupsContaining(String groupName) {
        return groupRepository.findAllByGroupNameContaining(groupName);
    }
}
