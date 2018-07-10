package org.ictlab.repository;

import org.ictlab.domain.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule, Long> {
    List<SchoolSchedule> findAllByStartGreaterThanAndEndLessThan(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
    SchoolSchedule findByStartLessThanEqualAndEndGreaterThanEqual(@NotNull LocalDateTime start, @NotNull LocalDateTime end);

    @Query(value = "SELECT * FROM schoolschedule WHERE id = :id", nativeQuery = true)
    SchoolSchedule findId(@Param("id") Long id);

    @Query(value = "SELECT * FROM schoolschedule WHERE startdate BETWEEN :startStartCheck AND :startEndCheck OR enddate BETWEEN :endStartCheck AND :endEndCheck", nativeQuery = true)
    List<SchoolSchedule> existsByStartBetweenAndEnd(@Param("startStartCheck") LocalDateTime start, @Param("startEndCheck") LocalDateTime startCheck, @Param("endStartCheck") LocalDateTime endCheck, @Param("endEndCheck") LocalDateTime end);
}
