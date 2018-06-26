package org.ictlab.repository;

import org.ictlab.domain.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule, Long> {
    List<SchoolSchedule> findAllByStartGreaterThanAndEndLessThan(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
    SchoolSchedule findByStartLessThanEqualAndEndGreaterThanEqual(@NotNull LocalDateTime start, @NotNull LocalDateTime end);
}
