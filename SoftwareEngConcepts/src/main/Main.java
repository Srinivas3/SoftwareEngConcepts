package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

import actionHandler.TAActionHandler;
import cse.sec.team26.reservation.Reservation;
import cse.sec.team26.reservation.ReservationHandler;

public class Main {
	static String[] columnNames = { "Name", "Email", "Question", "Time" };

	public static void main(String[] args) {
		ReservationHandler reservationHandler = new ReservationHandler();
		reservationHandler.populateReservationQueue();
		displayCurrentReservation(reservationHandler);

	}
	public static void displayCurrentReservation(ReservationHandler reservationHandler) {

		TAActionHandler taActionHandler =  new TAActionHandler(reservationHandler);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		Reservation reservation = reservationHandler.getCurrentReservation();
		JLabel textArea = new JLabel();
		textArea.setBounds(300, 50, 200, 200);
		JButton buttonClose = new JButton("Close");
		
		buttonClose.setBounds(200, 450, 120, 50);
		buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();

			}
		});

		JButton buttonPresent = new JButton("Present");
		buttonPresent.setBounds(140, 450, 120, 50);
		buttonPresent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				//remove from queue
				//update  completed, banned queue
				taActionHandler.handleStudentPresent();
				displayQueueStatus(reservationHandler, "The appointment was completed.");
				
			}
		});
		
		JButton buttonAbsent = new JButton("Absent");
		buttonAbsent.setBounds(340, 450, 120, 50);
		buttonAbsent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				//calculate
				String status = taActionHandler.handleStudentAbsent();
				displayQueueStatus(reservationHandler, status);

			}
		});
		
		
		if(reservation != null) {


			StringBuilder sb = new StringBuilder();
			sb.append(reservation.getName() + "\n");
			sb.append("\n");
			sb.append(reservation.getEmail() + "\n");
			sb.append(reservation.getQuestion() + "\n");
			sb.append(reservation.getDate().toString() + "\n");
			String template = "<html><div style='text-align: center;'>Name : %s<br/>Email : %s<br/>Question : %s<br/>Reservation Date : %s</div>";
			String text = String.format(template, reservation.getName(), reservation.getEmail(),reservation.getQuestion(),reservation.getDate());
			textArea.setText(text);
			// create a label to display text


			frame.add(buttonPresent);
			frame.add(buttonAbsent);
		}else {
			textArea.setText("No Reservations at this time!!");
			frame.add(buttonClose);
		}
		frame.add(textArea);
		frame.setVisible(true);
	}

	public static void displayQueueStatus(ReservationHandler reservationHandler, String status) {

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(600, 700);
		f.setLayout(new GridLayout(3,1));
		JLabel label = new JLabel("Reservation Queue Status");
		Object[][] data = reservationHandler.getQueueStatus(reservationHandler.getReservationQueue());
		JTable table = new JTable(data, columnNames);
		table.setBounds(100, 200, 200, 300);
		JScrollPane scrollPane = new JScrollPane(table);
		JLabel statusText = new JLabel(status);
		f.add(label);
		f.add(scrollPane);
		f.add(statusText);
		f.setVisible(true);
//		System.exit(1);
	
	}

}
