package org.ictlab.rest;

import org.ictlab.Service.ReservationService;
import org.ictlab.domain.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/simple")
    public void createReservation(@RequestBody Reservation reservation) {
        log.info("Added a reservation");
        reservationService.createOrUpdate(reservation);
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
