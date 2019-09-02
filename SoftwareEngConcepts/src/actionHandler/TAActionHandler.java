package actionHandler;

import cse.sec.team26.reservation.Reservation;
import cse.sec.team26.reservation.ReservationHandler;

import java.util.Date;

public class TAActionHandler {

    ReservationHandler reservationHandler = null;

    public TAActionHandler(ReservationHandler reservationHandler) {

        this.reservationHandler = reservationHandler;
    }

    public void handleStudentPresent(){

        reservationHandler.removeFirst();
    }

    public String handleStudentAbsent() {

        Reservation currentReservation = reservationHandler.removeFirst();
        Date reservationDate = currentReservation.getDate();
        //less than or equal to 5 min late
        if(!isStudentBanned(reservationDate.getTime(), reservationHandler.getCurrentTime())) {
            //post to end of queue
            reservationHandler.addLastToQueue(currentReservation);
            return "Appointment Rescheduled at the end of the queue.";

        }else {
            return "Student banned on : " + new Date(reservationHandler.getCurrentTime());
        }
    }


    public boolean isStudentBanned(long reservationTime, long currentTime){
//        return (Math.abs(reservationTime - currentTime) <= 5*60000);
        return (currentTime - reservationTime > 5*60000);
    }

}
