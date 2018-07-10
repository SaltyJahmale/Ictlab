package org.ictlab.rest;

import org.ictlab.Service.*;
import org.ictlab.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/schoolschedule")
public class SchoolScheduleController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final SchoolScheduleService schoolScheduleService;
    private final ReservationService reservationService;
    private final GroupService groupService;
    private final UserService userService;
    final private MessageService messageService;

    @Autowired
    public SchoolScheduleController(SchoolScheduleService schoolScheduleService, ReservationService reservationService, GroupService groupService, UserService userService, MessageService messageService) {
        this.schoolScheduleService = schoolScheduleService;
        this.reservationService = reservationService;
        this.groupService = groupService;
        this.userService = userService;
        this.messageService = messageService;
    }

    /**
     * @param schoolSchedule
     * @param groupName
     * @return Http status code
     */
    @PostMapping(value = "/{groupName}")
    public ResponseEntity createSchoolSchedule(@RequestBody SchoolSchedule schoolSchedule,
                                               @PathVariable("groupName") String groupName) {

        LocalDateTime end = LocalDateTime.parse(schoolSchedule.getEnd().toString());
        LocalDateTime start = LocalDateTime.parse(schoolSchedule.getStart().toString());

        if (!reservationService.checkAvailableStartAndEndDate(start, end, start, end).isEmpty()) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (!schoolScheduleService.checkAvailableStartAndEndDate(start, end, start, end).isEmpty()) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (end.isBefore(start)) {
            log.info(String.format("End date %s should be bigger that start date %s", end, start));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Group group = groupService.getGroupByName(groupName);
        List<User> users = userService.getAllByClass(group);
        schoolSchedule.setUsers(users);
        schoolScheduleService.saveSchoolSchedule(schoolSchedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param start
     * @param end
     * @return List<SchoolSchedule>
     */
    @GetMapping(value = "/{start}{end}")
    public List<SchoolSchedule> getAllSchoolSchedulesBetweenDates(
        @PathVariable("start") LocalDateTime start,
        @PathVariable("end") LocalDateTime end) {
        return schoolScheduleService.getAllSchoolSchedulesBetweenDates(start, end);
    }

    /**
     * @return List<SchoolSchedule>
     */
    @GetMapping
    public List<SchoolSchedule> getAllSchoolSchedules() {
        return schoolScheduleService.getAllSchoolSchedules();
    }

    /**
     * @param schoolSchedule
     * @return Http status code
     */
    @PutMapping(value = "/edit")
    public ResponseEntity updateSchedule(@RequestBody SchoolSchedule schoolSchedule) {

        LocalDateTime end = LocalDateTime.parse(schoolSchedule.getEnd().toString());
        LocalDateTime start = LocalDateTime.parse(schoolSchedule.getStart().toString());

        if (!reservationService.checkAvailableStartAndEndDate(start, end, start, end).isEmpty()) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (!schoolScheduleService.checkAvailableStartAndEndDate(start, end, start, end).isEmpty()) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (end.isBefore(start)) {
            log.info(String.format("End date %s should be bigger that start date %s", end, start));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        SchoolSchedule schoolSchedule1 = schoolScheduleService.getById(schoolSchedule.getId());
        schoolSchedule1.setId(schoolSchedule.getId());
        schoolSchedule1.setTitle(schoolSchedule.getTitle());
        schoolSchedule1.setStart(schoolSchedule.getStart());
        schoolSchedule1.setEnd(schoolSchedule.getEnd());
        schoolSchedule1.setRooms(schoolSchedule.getRooms());
        schoolSchedule1.setTeachers(schoolSchedule.getTeachers());
        schoolSchedule1.setUsers(schoolSchedule.getUsers());

        List<User> users = schoolSchedule.getUsers();
        Message message = new Message();
        message.setLocalDateTime(LocalDateTime.now());
        for (User user : users) {
            message.setMsg(String.format("There was a change in schedule %s %s %s", schoolSchedule1.getTitle(), end, start));
            message.setState(State.CHANGED);
            user.getMessages().add(message);
            userService.saveUser(user);
        }

        schoolScheduleService.saveSchoolSchedule(schoolSchedule1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param schoolSchedule
     * @param username
     * @return Http status code
     */
    @PutMapping(value = "/enroll/{username}")
    public ResponseEntity enroll(@RequestBody SchoolSchedule schoolSchedule,
                       @PathVariable("username") String username) {

        User user = userService.getUser(username);

        SchoolSchedule schoolSchedule1 = schoolScheduleService.getById(schoolSchedule.getId());
        schoolSchedule1.getUsers().add(user);

        schoolScheduleService.saveSchoolSchedule(schoolSchedule1);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @param id
     * @return SchoolSchedule
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SchoolSchedule> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(schoolScheduleService.getById(id), HttpStatus.OK);
    }

}
