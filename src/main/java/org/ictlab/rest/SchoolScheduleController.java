package org.ictlab.rest;

import org.ictlab.Service.ReservationService;
import org.ictlab.Service.SchoolScheduleService;
import org.ictlab.domain.Reservation;
import org.ictlab.domain.SchoolSchedule;
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

    @Autowired
    public SchoolScheduleController(SchoolScheduleService schoolScheduleService, ReservationService reservationService) {
        this.schoolScheduleService = schoolScheduleService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity createSchoolSchedule(@RequestBody SchoolSchedule schoolSchedule) {

        LocalDateTime end = LocalDateTime.parse(schoolSchedule.getEnd().toString());
        LocalDateTime start = LocalDateTime.parse(schoolSchedule.getStart().toString());

        Reservation reservationExists = reservationService.findByDate(start, end);
        System.out.println("Reservation date" + reservationExists);
        if(reservationExists != null) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        SchoolSchedule schoolScheduleExists = schoolScheduleService.findByDate(start, end);
        System.out.println("Schoolschedule date" + schoolScheduleExists);
        if(schoolScheduleExists != null) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if(end.isBefore(start)) {
            log.info(String.format("End date %s should be bigger that start date %s", end, start));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        schoolScheduleService.saveSchoolSchedule(schoolSchedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{start}{end}")
    public List<SchoolSchedule> getAllSchoolSchedulesBetweenDates(
        @PathVariable("start") LocalDateTime start,
        @PathVariable("end") LocalDateTime end) {
        return schoolScheduleService.getAllSchoolSchedulesBetweenDates(start, end);
    }

    @GetMapping
    public List<SchoolSchedule> getAllSchoolSchedules() {
        return schoolScheduleService.getAllSchoolSchedules();
    }
}
