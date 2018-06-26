package org.ictlab.rest;

import org.ictlab.Service.ReservationService;
import org.ictlab.Service.SchoolScheduleService;
import org.ictlab.Service.UserService;
import org.ictlab.domain.Reservation;
import org.ictlab.domain.SchoolSchedule;
import org.ictlab.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ReservationService reservationService;
    private final UserService userService;
    private final SchoolScheduleService schoolScheduleService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, SchoolScheduleService schoolScheduleService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.schoolScheduleService = schoolScheduleService;
    }

    @PostMapping(value = "/simple/{username}")
    public ResponseEntity createReservation(@RequestBody Reservation reservation,
                                            @PathVariable("username") String username) {

        LocalDateTime end = LocalDateTime.parse(reservation.getEnd().toString());
        LocalDateTime start = LocalDateTime.parse(reservation.getStart().toString());

        User userExists = userService.getUser(username);
        if(userExists == null) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reservation reservationExists = reservationService.findByDate(start, end);
        System.out.println(reservationExists);
        if(reservationExists != null) {
            log.info(String.format("Date already exists between %s and %s", reservation.getStart(), reservation.getEnd()));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        SchoolSchedule schoolScheduleExists = schoolScheduleService.findByDate(start, end);
        System.out.println(schoolScheduleExists);
        if(schoolScheduleExists != null) {
            log.info(String.format("Date already exists between %s and %s", start, end));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if(end.isBefore(start)) {
            log.info(String.format("End date %s should be bigger that start date %s", end, start));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        User user = userService.getUser(username);
//        user.getReservations().add(reservation);
//        userService.saveUser(user);
        log.info(String.format("Added a reservation for user with username: %s", username));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Optional<Reservation> reservationById(@PathVariable("id") Long id) {
        log.info(String.format("Got the reservation with id: %s", id));
        return reservationService.findById(id);
    }


    @GetMapping(value = "/reservations")
    public Collection<Reservation> reservationList() {
        log.info("Returned a list of reservations");
        return reservationService.findAll();
    }

    @GetMapping(value = "/{start}{end}")
    public Collection<Reservation> reservationsBetweenDates(
        @PathVariable("start") LocalDateTime start,
        @PathVariable("end") LocalDateTime end) {

        log.info(String.format("Returning reservations between dates start: %s  end: %s", start, end));
        return reservationService.findInBetweenDates(start, end);
    }

}
