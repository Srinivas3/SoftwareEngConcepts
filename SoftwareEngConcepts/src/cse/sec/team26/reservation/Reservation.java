package cse.sec.team26.reservation;
import java.util.*;
public class Reservation {
	String email;
	String question;
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	Date date; 
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Email:"+this.email);
		stringBuilder.append("\n");
		stringBuilder.append("Question:"+this.question);
		stringBuilder.append("\n");
		stringBuilder.append("Date:"+this.date);
		stringBuilder.append("\n");
		return stringBuilder.toString(); 
	}
}
