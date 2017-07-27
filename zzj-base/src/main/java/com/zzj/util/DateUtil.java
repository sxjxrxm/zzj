/**
 * Project Name:zzj-base
 * File Name:DateUtil.java
 * Package Name:com.zzj.util
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * <p><strong>类名: </strong></p>DateUtil <br/>
 * <p><strong>功能说明: </strong></p>日期工具类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月7日下午1:08:32 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);

	// yyyy-MM-dd
	private static String datePattern = "yyyy-MM-dd";
	// yyyy-MM-dd HH:mm:ss
	private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	// yyyy-MM-dd HH:mm
	private static String dateTime_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	// yyyyMMddHHmmssSSS
	private static String dateTime_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	private static String timePattern = "HHmm";
	// yyyy-MM-dd
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	// yyyy-MM-dd HH:mm:ss
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimePattern);
	// yyyyMMddHHmmssSSS
	private static SimpleDateFormat timeFormat = new SimpleDateFormat(dateTime_yyyyMMddHHmmssSSS);
	// yyyy-MM-dd HH:mm
	private static SimpleDateFormat time_yyyy_MM_dd_HH_mm = new SimpleDateFormat(dateTime_yyyy_MM_dd_HH_mm);

	/**
	 * 将字符串转换成仅包含日期数据的Date对象yyyy-MM-dd
	 * @param dateStr  日期字符串
	 * @return Date Date对象
	 * **/
	public static Date getDateFormat(String dateStr) {
		Date date = null;
        try {
			if(null!=dateStr&&!"".equals(dateStr))
				date = dateFormat.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 将字符串转换成仅包含日期数据的Date对象yyyy-MM-dd HH:mm
	 * @param dateStr  日期字符串
	 * @return Date Date对象
	 * **/
	public static Date getDateFormatYMDhm(String dateStr) {
		Date date = null;
        try {
			if(null!=dateStr&&!"".equals(dateStr))
				date = time_yyyy_MM_dd_HH_mm.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 将Date对象转换成带日期和时分的日期字符串，格式为yyyy-MM-dd HH:mm
	 * @param  date  日期对象
	 * @return String 日期字符串
	 * **/
	public static String getYmdHmFormatString(Date date){
		String dateTimeFormatString = null;
        try {
			if(null!=date)
				dateTimeFormatString = time_yyyy_MM_dd_HH_mm.format(date);
		} catch (Exception e) {
		}
		return dateTimeFormatString;
	}
	
	/**
	 * 将Date对象转换成带日期和时分秒的日期字符串，格式为yyyy-MM-dd HH:mm:ss
	 * @param  date  日期对象
	 * @return String 日期字符串
	 * **/
	public static String getDateTimeFormatString(Date date){
		String dateTimeFormatString = null;
        try {
			if(null!=date)
				dateTimeFormatString = dateTimeFormat.format(date);
		} catch (Exception e) {
		}
		return dateTimeFormatString;
	}

	/**
	 * 将Date对象转换成带日期和时分秒的日期字符串，格式为yyyyMMddHHmmssSSS
	 * @param  date  日期对象
	 * @return String 日期字符串
	 * **/
	public static String getDateTimeStr(Date date) {
		String dateTimeFormatString = null;
        try {
			if(null!=date)
				dateTimeFormatString = timeFormat.format(date);
		} catch (Exception e) {
		}
		return dateTimeFormatString;
	}
	
	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat(datePattern);
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 将字符串转换成包含日期和时间数据的Date对象
	 * @param dateStr  日期时间字符串
	 * @return Date Date对象
	 * **/
	public static Date getDateTimeFormat(String dateStr){
		Date date = null;
        try {
			if(null!=dateStr&&!"".equals(dateStr))
				date = dateTimeFormat.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}
	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to yyyy年MM月dd日.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
        if(null == aMask || "".equals(aMask)){
         aMask = datePattern;
        }
/*		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}*/
        if(null != strDate){
			try {
				date = df.parse(strDate);
			} catch (ParseException pe) {
				//log.error("ParseException: " + pe);
				throw new ParseException(pe.getMessage(), pe.getErrorOffset());
			}
        }
		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getDateTime(Date theTime) {
		return getDateTime(dateTimePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		
		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
//			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 * 
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * 
	 * @param aMask
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(String aMask, Date aDate) {
		return getDateTime(aMask, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + datePattern);
			}
			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	public static Date stringToDate(String param) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(param);
		} catch (ParseException ex) {
		}
		return date;
	}

	public static Timestamp convertStringToTimestamp(String strDate) {
		java.util.Date date = null;

		try {
			date = convertStringToDate(datePattern, strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date == null ? null : new Timestamp(date.getTime());
	}

	public static Timestamp convertStringToTimestamp(String aMask, String strDate) {
		java.util.Date date = null;
		try {
			date = convertStringToDate(aMask, strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date == null ? null : new Timestamp(date.getTime());
	}

	public static final String convertTimestampToString(String aMask, Timestamp aDate) {
		return getDateTime(aMask, aDate);

	}

	public static final String convertTimestampToString(Timestamp aDate) {
		return getDateTime(datePattern, aDate);

	}

	public static Timestamp getNowDateToTimestamp(String aMask) {
		return convertStringToTimestamp(aMask, getDate(aMask, new Date()));
	}

	public static final String getDate(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static String getLastMon() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(2, -1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return df.format(gc.getTime());
	}
	
	//获得日期的0点
	public static final Date getDayBeginTime(Date aDate){
		try{
			String daystr = getDateTime("yyyyMMdd", aDate);
			daystr=daystr+"000000";
			return convertStringToDate("yyyyMMddHHmmss",daystr);
		}
		catch(Exception e){
			return null;
		}
	}
	
	//获得日期的23点59分59秒
	public static final Date getDayEndTime(Date aDate){
		try{
			String daystr = getDateTime("yyyyMMdd", aDate);
			daystr=daystr+"235959";
			return convertStringToDate("yyyyMMddHHmmss",daystr);
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 获取前三年的年份
	 * @param
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	public static List<Integer> getBeforeThreeYears(){
		  List<Integer> result = new ArrayList<Integer>(3);
		  Calendar calendar = Calendar.getInstance();
			  int thisYear = calendar.get(Calendar.YEAR);
			  result.add(thisYear);
			  result.add(thisYear-1);
			  result.add(thisYear-2);
		  return result;
	}
	
	/**
	 * 判断是否是日期
	 * @param  str ,日期字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 * @throws Exception
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
			format.setLenient(false);
			format.parse(str);
		} catch (Exception e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		}
		return convertSuccess;
	}
	
	/**
	 * 比较两日期time1是否大于time2 至少1天
	 * @param  time1 ,时间
	 * @param  time2 ,时间
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 * @throws Exception
	 */
	public static boolean judgeTime(String time1, String time2){
		Date date1 = getDateFormat(time1);
		Date date2 = getDateFormat(time2);
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		long time = l1 - l2;
		if(time >= 86400000){
			return true;
		}
		return false;
	}
	
	/**
	 * 在传的日期上加多少天
	 * @param  startdate ,日期
	 * @param  day ,天数
	 * @return 日期字符串
	 * @throws Exception
	 */
	public static String addDay(String startdate,Integer day){
		try {
			Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(startdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, day);
			return getDate(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 在传的日期上加多少月
	 * @param  startdate ,日期
	 * @param  mon ,月
	 * @return 日期字符串
	 * @throws Exception
	 */
	public static String addMon(String startdate,Integer mon){
		try {
			Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(startdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, mon);
			return getDate(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 在传的日期上加多少年
	 * @param  startdate ,日期
	 * @param  year ,年
	 * @return 日期字符串
	 * @throws Exception
	 */
	public static String addYear(String startdate,Integer year){
		try {
			Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(startdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, year);
			return getDate(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取指定日期的年份
	 * @param  date ,日期
	 * @return int year ,年
	 */
	public static Integer getYear(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.YEAR);
	}
	
	/**
	 * 获取指定日期的月份
	 * @param  date ,日期
	 * @return int month ,月
	 */
	public static Integer getMonth(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.MONTH);
	}
	
	/**
	 * 获取指定日期的天
	 * @param  date ,日期
	 * @return int day 天
	 */
	public static Integer getDay(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取相隔月份
	 * @param  startDate 开始日期
	 * @param  endDate 结束日期
	 * @return int month 月
	 */
	public static Integer getBetweenMon(Date startDate, Date endDate){
		Integer month = 0;
		try {
			// 开始时间值计算
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(startDate);
			
			int startDay = startCal.get(Calendar.DATE);
			int startMonth = startCal.get(Calendar.MONTH);
			int startYear = startCal.get(Calendar.YEAR);

			// 结束时间值计算
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			
			int endDay = endCal.get(Calendar.DATE);
			int endMonth = endCal.get(Calendar.MONTH);
			int endYear = endCal.get(Calendar.YEAR);
			
			// 间隔年计算
			int year = endYear - startYear;
			// 结束日<=开始日时，借一月，结束月-1
			if(endDay - startDay <= 0){
				endMonth--;
			}
			// 结束月<=开始月时，借一年，间隔年-1
			month = endMonth - startMonth;
			if(month < 0) {
				year--;
				month += 12;
			}
			// 间隔年<0时，设置为0
			if(year < 0){
				year = 0;
			}
			
			// 最后间隔月
			month = year * 12 + month;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month;
	}
	
	/**
	 * 获取相隔年份
	 * @param  startDate 开始日期
	 * @param  endDate 结束日期
	 * @return Integer 间隔年(周岁)
	 */
	public static Integer getBetweenYear(Date startDate, Date endDate){
		int month = DateUtil.getBetweenMon(startDate, endDate);
		return month / 12;
	}
	
	/**
	 * 时间格式化处理（HHMM格式）<br/>
	 * @param  date 需要处理的时间对象
	 * @return String 格式化后的时间字符串（HHMM）
	 * @throws Exception
	 */
	public static String getTimeHHMM(Date date){
		return getDateTime(timePattern, date);
	}

	public static void main(String[] args) {
		try {
			Date startDate = DateUtil.convertStringToDate("1984-07-12");
			Date endDate = DateUtil.convertStringToDate("2016-07-12");
			int year = DateUtil.getBetweenYear(startDate, endDate);
			int month = DateUtil.getBetweenMon(startDate, endDate);
			int day = DateUtil.daysBetween(startDate, endDate);

			System.out.println("year :" + year);
			System.out.println("month :" + month);
			System.out.println("day :" + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

