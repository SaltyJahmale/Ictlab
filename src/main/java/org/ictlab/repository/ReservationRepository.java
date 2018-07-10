package org.ictlab.repository;

import org.ictlab.domain.Reservation;
import org.ictlab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Collection<Reservation> findAllByStartGreaterThanAndEndLessThan(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
    Reservation findByStartLessThanEqualAndEndGreaterThanEqual(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
    List<Reservation> findAllByUsers_id(Long id);

    @Query(value = "SELECT * FROM reservation WHERE startdate BETWEEN :startStartCheck AND :startEndCheck OR enddate BETWEEN :endStartCheck AND :endEndCheck", nativeQuery = true)
    List<Reservation> existsByStartBetweenAndEnd(@Param("startStartCheck") LocalDateTime start, @Param("startEndCheck") LocalDateTime startCheck, @Param("endStartCheck") LocalDateTime endCheck, @Param("endEndCheck") LocalDateTime end);

}
