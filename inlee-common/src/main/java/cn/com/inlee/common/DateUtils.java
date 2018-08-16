package cn.com.inlee.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtils {

	/**
	 * 获取两日期之间间隔的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static boolean isBetween(Date date, Date startDate, Date endDate) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));
			date = sdf.parse(sdf.format(date));

			Calendar cal = Calendar.getInstance();

			cal.setTime(date);
			long time0 = cal.getTimeInMillis();
			cal.setTime(startDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endDate);
			long time2 = cal.getTimeInMillis();

			if (time0 > time1 && time0 <= time2)
				return true;

		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 比较两个时间
	 * 
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static long compareMillis(Date sDate, Date eDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate = sdf.parse(sdf.format(sDate));
			eDate = sdf.parse(sdf.format(eDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(sDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(eDate);			
			long time2 = cal.getTimeInMillis();
			return time1 - time2;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return -1L;
	}

	public static boolean isToday(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String _date = sdf.format(date);
		String _today = sdf.format(new Date());

		return _date.equals(_today);
	}

	public static long DifferMillis(Date sDate, Date eDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate = sdf.parse(sdf.format(sDate));
			eDate = sdf.parse(sdf.format(eDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(sDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(eDate);
			long time2 = cal.getTimeInMillis();
			return Math.abs(time1 - time2);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return -1L;
	}

	public static long DifferSeconds(Date sDate, Date eDate) {

		return DifferMillis(sDate, eDate) / 1000;
	}

	/**
	 * 相差分钟数
	 * 
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static long DifferMinutes(Date sDate, Date eDate) {

		return Long.parseLong(String.valueOf(DifferMillis(sDate, eDate) / (1000 * 60)));
	}

	public static String getNowDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	public static String getTomorrowDate(String format) {
		return new SimpleDateFormat(format).format(addDate(new Date(), 1));
	}

	public static String getDateString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static Date parse(String date, String format) {

		try {
			return new SimpleDateFormat(format).parse(date);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean nowTimeBetween(String beginTimeStr, String endTimeStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Long beginTime = dateFormat.parse(beginTimeStr).getTime();
		Long endTime = dateFormat.parse(endTimeStr).getTime();
		Long nowTime = dateFormat.parse(dateFormat.format(new Date())).getTime();
		if (nowTime >= beginTime && nowTime <= endTime) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public static Date addDate(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	@SuppressWarnings("static-access")
	public static Date addHour(Date date, int hour) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.HOUR, hour);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	@SuppressWarnings("static-access")
	public static Date addMinute(Date date, int minute) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MINUTE, minute);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	@SuppressWarnings("static-access")
	public static Date addSecond(Date date, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.SECOND, second);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 
	 * @param timestamp
	 *            秒
	 * @return
	 */
	public static Date getDate(String timestamp) {
		return getDateBySecond(Long.parseLong(timestamp)); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 
	 * @param timestamp
	 *            毫秒
	 * @return
	 */
	public static Date getDateByMillis(long timestamp) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timestamp);
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 
	 * @param timestamp
	 *            秒
	 * @return
	 */
	public static Date getDateBySecond(long timestamp) {
		return getDateByMillis(timestamp * 1000); // 这个时间就是日期往后推一天的结果
	}

	public static long getTimestamp(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.getTimeInMillis(); // 这个时间就是日期往后推一天的结果
	}

	public static long DifferMillis(String timestamp, String nowTimestamp) {
		Date date1 = parse(timestamp, DATE_TIME_PATTERN);
		Date date2 = parse(nowTimestamp, DATE_TIME_PATTERN);

		return DifferMillis(date1, date2);
	}

	public static long DifferMillis(String timestamp, Date date) {
		Date date1 = parse(timestamp, DATE_TIME_PATTERN);

		return DifferMillis(date1, date);
	}

	public static List<Date> getWeekDays(Date date) {

		List<Date> result = new ArrayList<Date>();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		// String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		result.add(new Date(cal.getTimeInMillis()));

		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));

		cal.add(Calendar.DATE, 1);
		result.add(new Date(cal.getTimeInMillis()));
		// String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return result;
	}

	public static String getTimeInterval(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	public static String getChineseWeek(Date date) {

		String week = "星期";
		int day = getChineseDayOfWeek(date);
		switch (day) {
		case 1:
			week += "一";
			break;
		case 2:
			week += "二";
			break;
		case 3:
			week += "三";
			break;
		case 4:
			week += "四";
			break;
		case 5:
			week += "五";
			break;
		case 6:
			week += "六";
			break;
		case 0:
			week += "日";
			break;
		default:
			week += "日";
			break;

		}

		return week;

	}

	public static int getChineseDayOfWeek(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day > 0)
			return cal.get(Calendar.DAY_OF_WEEK) - 1;// 获得当前日期是一个星期的第几天
		else
			return 7;

	}

	public static void main(String[] args) {

		int date = DateUtils.daysBetween(new Date(), DateUtils.addDate(new Date(), +1));

		System.out.println(date);
	}

	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
}