package org.ictlab.rest;

import org.ictlab.Service.RoomService;
import org.ictlab.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
public class RoomController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {

        log.info("Succesfully returned a list of groups");
        return roomService.getAllRooms();
    }
}
