package org.ictlab.Service;

import org.ictlab.domain.Reservation;
import org.ictlab.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createOrUpdate(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation findReservationById(Long id) {
        return reservationRepository.getOne(id);
    }

    public List<Reservation> getReservationByUserId(Long id) {
        return reservationRepository.findAllByUsers_id(id);
    }

    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Collection<Reservation> findInBetweenDates(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.findAllByStartGreaterThanAndEndLessThan(start, end);
    }

    public List<Reservation> checkAvailableStartAndEndDate(LocalDateTime startStartCheck, LocalDateTime startEndCheck, LocalDateTime endStartCheck, LocalDateTime endEndCheck) {
        return reservationRepository.existsByStartBetweenAndEnd(startStartCheck, startEndCheck, endStartCheck, endEndCheck);
    }

}
