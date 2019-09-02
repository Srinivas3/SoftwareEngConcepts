package cse.sec.team26.reservation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyDate {
	private static Date date;
	public static Date getInstance() {
		if(date==null) {
			date = new Date();
		}
		return date;
	}
}
