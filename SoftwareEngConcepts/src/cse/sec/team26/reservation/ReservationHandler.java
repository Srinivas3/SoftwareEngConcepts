package cse.sec.team26.reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReservationHandler {

	HashMap<String, String> studentMap = new HashMap<>();
	List<Reservation> reservationQueue = new LinkedList<Reservation>();
	List<String> emails = new ArrayList<String>();
	List<String> questions = new ArrayList<String>();
	List<Date> dates = new ArrayList<Date>();
	long currentTime = System.currentTimeMillis();

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public void addToReservationQueue(Reservation reservation) {
		reservationQueue.add(reservation);
	}

	public void populateReservationQueue() {

		emails.add("student_1@buffalo.edu");
		emails.add("student_2@buffalo.edu");
		emails.add("student_3@buffalo.edu");
		emails.add("student_4@buffalo.edu");
		questions.add("This is a question - 1");
		questions.add("This is a question - 2");
		questions.add("This is a question - 3");
		questions.add("This is a question - 4");
		studentMap.put(emails.get(0), "Student_1");
		studentMap.put(emails.get(1), "Student_2");
		studentMap.put(emails.get(2), "Student_3");
		studentMap.put(emails.get(3), "Student_4");
		long timeInMillis = System.currentTimeMillis();
		setCurrentTime(timeInMillis);
		//TODO here the change is happening only in seconds. Change that to minutes
		dates.add(new Date(timeInMillis));
		dates.add(new Date(timeInMillis - 60 * 5000));
		dates.add(new Date(timeInMillis - 60 * 11000));

		Collections.shuffle(dates);
		Collections.shuffle(emails);
		Collections.shuffle(questions);
		int nReservations = new Random().nextInt(5);
		System.out.println(nReservations);
		int questionCounter = 0;
		while (nReservations > 0) {
			String question = "NO Question";
			int dateIndex = new Random().nextInt(3);
			String email = emails.get(0);
			emails.remove(0);
			if (questionCounter <= nReservations / 2) {
				question = questions.get(0);
				questions.remove(0);
				questionCounter++;
			}
			Date date = dates.get(dateIndex);
			//Reservation reservation = generateReservation(email, question, date);
			reservationQueue.add(generateReservation(studentMap.get(email), email, question, date));
			nReservations--;
		}

	}

	public Reservation generateReservation(String name, String email, String question, Date date) {
		Reservation reservation = new Reservation();
		reservation.setName(name);
		reservation.setEmail(email);
		reservation.setQuestion(question);
		reservation.setDate(date);
		return reservation;
	}

	public Object[][] getQueueStatus(List<Reservation> queue) {
		Object[][] data = new Object[queue.size()][];
		for (int i = 0; i < queue.size(); i++) {
			Reservation reservation = queue.get(i);
			data[i] = new Object[] { reservation.getName(), reservation.getEmail(), reservation.getQuestion(), reservation.getDate() };

		}
		return data;
	}

	public Reservation getCurrentReservation() {
		if(!reservationQueue.isEmpty()) {
			return ((LinkedList<Reservation>) reservationQueue).getFirst();
		}else {
			return null;
		}
		
	}
	
	public void addLastToQueue(Reservation reservation) {
		this.reservationQueue.add(reservation);
	}

	public List<Reservation> getReservationQueue() {
		return reservationQueue;
	}
	
	public Reservation removeFirst() {
		return ((LinkedList<Reservation>) reservationQueue).removeFirst();
	}

	public boolean isStudentBanned(Date date){
		return (Math.abs(date.getTime() - getCurrentTime()) <= 5000*60);
	}
	
	
}
