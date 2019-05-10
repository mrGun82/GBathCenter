package g.oa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSSM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	public static final String currentYMDHS() {
		return currentYMDHS(new Date());
	}
	public static final String currentYMDHS(Date date) {
		return SDF_YYYYMMDDHHMMSS.format(new Date());
	}
	public static final String transformToViewYYYYMMDDHHMMSSM(String YYYYMMDDHHMMSS) throws ParseException {
		return SDF_YYYYMMDDHHMMSSM.format(SDF_YYYYMMDDHHMMSS.parse(YYYYMMDDHHMMSS));
	}
	
	public static final String firstDayCurrentOfWeek() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		cal.add(Calendar.DATE, -day_of_week);
		return SDF_YYYYMMDD.format(cal.getTime());
	}
	public static final String lastDayOfCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		cal.add(Calendar.DATE, -day_of_week);
		cal.add(Calendar.DATE, 6);
		return SDF_YYYYMMDD.format(cal.getTime());
	}
	
	
}
