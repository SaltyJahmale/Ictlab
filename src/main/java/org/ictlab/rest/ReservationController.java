package org.ictlab.rest;

import org.ictlab.Service.ReservationService;
import org.ictlab.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {


    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/simple")
    public void createReservation(@RequestBody Reservation reservation) {
        reservationService.createOrUpdate(reservation);
    }

    @GetMapping(value = "/{id}")
    public Optional<Reservation> reservationById(@PathVariable("id") Long id) {
        return reservationService.findById(id);
    }


    @GetMapping(value = "/reservations")
    public Collection<Reservation> reservationList() {
        return reservationService.findAll();
    }

    @GetMapping(value = "/{start}{end}")
    public Collection<Reservation> reservationsBetweenDates(
        @PathVariable("start") LocalDateTime start,
        @PathVariable("end") LocalDateTime end) {
        return reservationService.findInBetweenDates(start, end);
    }

}
