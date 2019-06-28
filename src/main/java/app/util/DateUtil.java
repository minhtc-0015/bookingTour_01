package app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public java.sql.Date dateUtil(String format) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(format);
		String dateString = df.format(date);
		java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
		return sqlDate;
	}
}
