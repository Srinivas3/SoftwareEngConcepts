package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import cse.sec.team26.reservation.Reservation;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class ReservationTester {

    @Test
    void testReservationCreation() {
        Reservation reservation = new Reservation();
        Date date = new Date();

        reservation.setDate(date);
        reservation.setEmail("abc@gmail.com");
        reservation.setName("student1");
        reservation.setQuestion("question1");
        assertEquals(date, reservation.getDate());
        assertEquals("abc@gmail.com", reservation.getEmail());
        assertEquals("student1", reservation.getName());
        assertEquals("question1", reservation.getQuestion());
    }

}
