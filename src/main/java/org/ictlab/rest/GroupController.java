package org.ictlab.rest;

import org.ictlab.Service.GroupService;
import org.ictlab.domain.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/classes")
public class GroupController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {

        if(groupService.getAllGroups().isEmpty()) {
            log.info("No list of groups found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Succesfully returned a list of groups");
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @GetMapping(value = "/{groupName}")
    public ResponseEntity<List<Group>> getAllByName(@PathVariable("groupName") String groupName) {

        Group group = groupService.getGroupByName(groupName);
        if(group != null) {
            log.info(String.format("No group with groupname: %s could be found", groupName));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Succesfully returned a group");
        return new ResponseEntity<>(groupService.getAllGroupsContaining(), HttpStatus.OK);
    }

}
