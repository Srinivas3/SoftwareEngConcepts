package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import actionHandler.TAActionHandler;
import cse.sec.team26.reservation.Reservation;
import cse.sec.team26.reservation.ReservationHandler;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class TAActionHandlerTest {

    @Test
    void testStudentPresentOneReservationInQueue(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        Reservation reservation = new Reservation();
        Date date = new Date();
        reservation.setDate(date);
        reservation.setEmail("abc@gmail.com");
        reservation.setName("student1");
        reservation.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation);
        taActionHandler.handleStudentPresent();
        assertEquals(0 , reservationHandler.getReservationQueue().size());
    }


    @Test
    void testStudentPresentMultipleReservationsInQueue(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);

        Reservation reservation1 = new Reservation();
        Date date = new Date();
        reservation1.setDate(date);
        reservation1.setEmail("abc@gmail.com");
        reservation1.setName("student1");
        reservation1.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation1);

        Reservation reservation2 = new Reservation();
        Date date2 = new Date();
        reservation2.setDate(date2);
        reservation2.setEmail("abc@gmail.com");
        reservation2.setName("student1");
        reservation2.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation2);

        Reservation reservation3 = new Reservation();
        Date date3 = new Date();
        reservation3.setDate(date3);
        reservation3.setEmail("abc@gmail.com");
        reservation3.setName("student1");
        reservation3.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation3);

        Reservation reservation4 = new Reservation();
        Date date4 = new Date();
        reservation4.setDate(date4);
        reservation4.setEmail("abc@gmail.com");
        reservation4.setName("student1");
        reservation4.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation4);

        int initialQueueSize = reservationHandler.getReservationQueue().size();

        assertEquals(initialQueueSize , reservationHandler.getReservationQueue().size());
        taActionHandler.handleStudentPresent();
        initialQueueSize = initialQueueSize - 1;
        assertEquals(initialQueueSize , reservationHandler.getReservationQueue().size());
        taActionHandler.handleStudentPresent();
        initialQueueSize = initialQueueSize - 1;
        assertEquals(initialQueueSize, reservationHandler.getReservationQueue().size());
        taActionHandler.handleStudentPresent();
        initialQueueSize = initialQueueSize - 1;
        assertEquals(initialQueueSize , reservationHandler.getReservationQueue().size());
        taActionHandler.handleStudentPresent();
        initialQueueSize = initialQueueSize - 1;
        assertEquals(initialQueueSize , reservationHandler.getReservationQueue().size());

    }

    @Test
    void testIsStudentBanned() {
        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);

        long time1  = new Date().getTime();
        long time2 = time1 - (60000 * 5) + 10; // five minutes before the reservation

        assertEquals(false, taActionHandler.isStudentBanned(time1, time2));
    }


    @Test
    void testIsStudentNotBanned() {
        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        long time1  = new Date().getTime();
        long time2 = - (60000 * 5) - 10; // five minutes before the reservation

        assertEquals(true, taActionHandler.isStudentBanned(time1, time2));
    }

    @Test
    void testStudentAbsentLessThanFiveMinDelay(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        long currentTime = reservationHandler.getCurrentTime();
        Reservation reservation1 = new Reservation();
        Date date1 = new Date(currentTime - (60000 * 5) + 10);
        reservation1.setDate(date1);
        reservation1.setEmail("abc@gmail.com");
        reservation1.setName("student1");
        reservation1.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation1);

        Reservation reservation2 = new Reservation();
        Date date2 = new Date(currentTime - 60000 * 7);
        reservation2.setDate(date2);
        reservation2.setEmail("abc@gmail.com");
        reservation2.setName("student1");
        reservation2.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation2);

        String status1 = taActionHandler.handleStudentAbsent();
        assertEquals("Appointment Rescheduled at the end of the queue.", status1);
        assertEquals(2, reservationHandler.getReservationQueue().size());
        assertEquals(reservation1,
                reservationHandler.getReservationQueue().get(reservationHandler.getReservationQueue().size() - 1));
        assertEquals(reservation2, reservationHandler.getReservationQueue().get(0));

    }

    @Test
    void testStudentAbsentLessThanFiveMinDelayOneReservation(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        long currentTime = reservationHandler.getCurrentTime();
        Reservation reservation1 = new Reservation();
        Date date1 = new Date(currentTime - (60000 * 5) + 10);
        reservation1.setDate(date1);
        reservation1.setEmail("abc@gmail.com");
        reservation1.setName("student1");
        reservation1.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation1);

        String status1 = taActionHandler.handleStudentAbsent();
        assertEquals("Appointment Rescheduled at the end of the queue.", status1);
        assertEquals(1, reservationHandler.getReservationQueue().size());
        assertEquals(reservation1,
                reservationHandler.getReservationQueue().get(reservationHandler.getReservationQueue().size() - 1));
        assertEquals(reservation1, reservationHandler.getReservationQueue().get(0));

    }

    @Test
    void testStudentAbsentMoreThanElevenMinDelay(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        long currentTime = reservationHandler.getCurrentTime();
        Reservation reservation1 = new Reservation();
        Date date1 = new Date(currentTime - (60000 * 11) - 10);
        reservation1.setDate(date1);
        reservation1.setEmail("abc@gmail.com");
        reservation1.setName("student1");
        reservation1.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation1);

        Reservation reservation2 = new Reservation();
        Date date2 = new Date(currentTime - 60000 * 7);
        reservation2.setDate(date2);
        reservation2.setEmail("abc@gmail.com");
        reservation2.setName("student1");
        reservation2.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation2);

        String status1 = taActionHandler.handleStudentAbsent();
//        assertEquals("Student banned on : " + new Date(currentTime), status);
        assertEquals("Student banned on : " + new Date(currentTime), status1);
        assertEquals(1, reservationHandler.getReservationQueue().size());
        assertEquals(reservation2,
                reservationHandler.getReservationQueue().get(reservationHandler.getReservationQueue().size() - 1));
        assertEquals(reservation2, reservationHandler.getReservationQueue().get(0));

    }


    void testStudentAbsentMoreThanElevenMinDelayOnReservation(){

        ReservationHandler reservationHandler = new ReservationHandler();
        TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
        long currentTime = reservationHandler.getCurrentTime();
        Reservation reservation1 = new Reservation();
        Date date1 = new Date(currentTime - (60000 * 11) - 10);
        reservation1.setDate(date1);
        reservation1.setEmail("abc@gmail.com");
        reservation1.setName("student1");
        reservation1.setQuestion("question1");
        reservationHandler.addToReservationQueue(reservation1);

        String status1 = taActionHandler.handleStudentAbsent();
//        assertEquals("Student banned on : " + new Date(currentTime), status);
        assertEquals("Student banned on : " + new Date(currentTime), status1);
        assertEquals(0, reservationHandler.getReservationQueue().size());

    }

}
