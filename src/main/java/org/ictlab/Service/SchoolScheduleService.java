package org.ictlab.Service;

import org.ictlab.domain.SchoolSchedule;
import org.ictlab.repository.SchoolScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolScheduleService {

    private final SchoolScheduleRepository schoolScheduleRepository;

    @Autowired
    public SchoolScheduleService(SchoolScheduleRepository schoolScheduleRepository) {
        this.schoolScheduleRepository = schoolScheduleRepository;
    }

    /**
     * @param schoolSchedule
     */
    public void saveSchoolSchedule(SchoolSchedule schoolSchedule) {
        schoolScheduleRepository.save(schoolSchedule);
    }

    /**
     * @param startStartCheck
     * @param startEndCheck
     * @param endStartCheck
     * @param endEndCheck
     * @return List<SchoolSchedule>
     */
    public List<SchoolSchedule> checkAvailableStartAndEndDate(LocalDateTime startStartCheck, LocalDateTime startEndCheck, LocalDateTime endStartCheck, LocalDateTime endEndCheck) {
        return schoolScheduleRepository.existsByStartBetweenAndEnd(startStartCheck, startEndCheck, endStartCheck, endEndCheck);
    }

    /**
     * @return List<SchoolSchedule>
     */
    public List<SchoolSchedule> getAllSchoolSchedules() {
        return schoolScheduleRepository.findAll();
    }

    /**
     * @param start
     * @param end
     * @return List<SchoolSchedule>
     */
    public List<SchoolSchedule> getAllSchoolSchedulesBetweenDates(LocalDateTime start, LocalDateTime end) {
        return schoolScheduleRepository.findAllByStartGreaterThanAndEndLessThan(start, end);
    }

    /**
     * @param id
     * @return SchoolSchedule
     */
    public SchoolSchedule getById(Long id) {
        return schoolScheduleRepository.getOne(id);
    }
    
}
