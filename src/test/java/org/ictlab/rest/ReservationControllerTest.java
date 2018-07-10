package org.ictlab.rest;

import org.ictlab.Service.ReservationService;
import org.ictlab.Service.SchoolScheduleService;
import org.ictlab.domain.Reservation;
import org.ictlab.domain.SchoolSchedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private SchoolScheduleService schoolScheduleService;

    @Test
    public void createReservation() {
        SchoolSchedule schoolSchedule = new SchoolSchedule();
        schoolSchedule.setId(1L);
        schoolSchedule.setTitle("mock");
//        schoolSchedule.set("mock");
//        reservationService.createOrUpdate();

    }
}
