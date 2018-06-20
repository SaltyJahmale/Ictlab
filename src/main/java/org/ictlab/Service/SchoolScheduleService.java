package org.ictlab.Service;

import org.ictlab.domain.SchoolSchedule;
import org.ictlab.repository.SchoolScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchoolScheduleService {

    private final SchoolScheduleRepository schoolScheduleRepository;

    @Autowired
    public SchoolScheduleService(SchoolScheduleRepository schoolScheduleRepository) {
        this.schoolScheduleRepository = schoolScheduleRepository;
    }

    public void saveSchoolSchedule(SchoolSchedule schoolSchedule) {
        schoolScheduleRepository.save(schoolSchedule);
    }

    public List<SchoolSchedule> getAllSchoolSchedules() {
        return schoolScheduleRepository.findAll();
    }

    public List<SchoolSchedule> getAllSchoolSchedulesBetweenDates(LocalDateTime start, LocalDateTime end) {
        return schoolScheduleRepository.findAllByStartGreaterThanAndEndLessThan(start, end);
    }

    
}
