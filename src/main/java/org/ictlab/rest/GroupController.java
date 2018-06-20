package org.ictlab.rest;

import org.ictlab.Service.GroupService;
import org.ictlab.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/classes")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping(value = "/{groupName}")
    public List<Group> getAllByName(@PathVariable("groupName") String groupName) {
        return groupService.getAllGroupsContaining(groupName);
    }

}
