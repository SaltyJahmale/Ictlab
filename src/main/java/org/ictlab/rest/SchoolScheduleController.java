package org.ictlab.rest;

import org.ictlab.Service.SchoolScheduleService;
import org.ictlab.domain.SchoolSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/schoolschedule")
public class SchoolScheduleController {

    private final SchoolScheduleService schoolScheduleService;

    @Autowired
    public SchoolScheduleController(SchoolScheduleService schoolScheduleService) {
        this.schoolScheduleService = schoolScheduleService;
    }

    @PostMapping
    public void createSchoolSchedule(@RequestBody SchoolSchedule schoolSchedule) {
        schoolScheduleService.saveSchoolSchedule(schoolSchedule);
    }

    @GetMapping(value = "/{start}{end}")
    public List<SchoolSchedule> getAllSchoolSchedulesBetweenDates(
        @PathVariable("start") LocalDateTime start,
        @PathVariable("end") LocalDateTime end) {
        return schoolScheduleService.getAllSchoolSchedulesBetweenDates(start, end);
    }

    @GetMapping
    public List<SchoolSchedule> getAllSchoolSchedules() {
        return schoolScheduleService.getAllSchoolSchedules();
    }
}
