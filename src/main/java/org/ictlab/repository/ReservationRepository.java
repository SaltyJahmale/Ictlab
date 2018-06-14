package org.ictlab.repository;

import org.ictlab.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Collection<Reservation> findAllByStartGreaterThanAndEndLessThan(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
}
