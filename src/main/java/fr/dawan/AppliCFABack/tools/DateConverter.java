package fr.dawan.AppliCFABack.tools;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public  class DateConverter {
	public static LocalDate convertToLocalDate(String dateToConvert) {
		ZonedDateTime date = ZonedDateTime.parse(dateToConvert);
	    return date.toLocalDate();
	}
}
