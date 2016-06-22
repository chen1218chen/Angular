package com.city.util;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

public class Common {

/*	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat todayformat = new SimpleDateFormat("yyyyMMdd");*/
	public static String standardDateFormat = "yyyy-MM-dd HH:mm:ss";
	public static String dateformat = "yyyyMMddHHmmss";
	public static String todayformat = "yyyyMMddb";
	
	public static SimpleDateFormat simpleFormat(String format){
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple;
	}
	
	public static String dateFormatStr(Date date){
		SimpleDateFormat simpleFormat = simpleFormat(standardDateFormat);
		return simpleFormat.format(date);
	}
	
	// 将字符串转换为日期类型
	public static Date toDateBase(String format,String timestr) throws ParseException {
		Date date = null;
		SimpleDateFormat simpleFormat = simpleFormat(format);
		date = simpleFormat.parse(timestr);
		return date;
	}
	public static Date toDate(String timestr) throws ParseException {
		return toDateBase(dateformat,timestr);
	}
	public static Date toDateStandard(String timestr) throws ParseException {
		return toDateBase(standardDateFormat,timestr);
	}
	
	public static Date toToday(String timestr) throws ParseException {
		Date date = null;
		SimpleDateFormat simpleFormat = simpleFormat(dateformat);
		date = simpleFormat.parse(timestr);
		return date;
	}
	// 将日期类型转换为字符串
	public static String dateToString(Date date) {
		SimpleDateFormat simpleFormat = simpleFormat(dateformat);
		return simpleFormat.format(date);
	}
	// 使用秒数移动日期，正为向后移动，负为向前移动
	public static Date moveBySecond(Date date, int span) {
		return new Date(date.getTime() + span * 1000);
	}

	private static JsonConfig jsonConfig = null;

	// 将Bean中Timestamp转换为json中的日期字符串
	public static JsonConfig getJsonConfig() {
		if (jsonConfig == null) {
			jsonConfig = new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				@Override
				public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
					if(arg1==null)
						return "";
					else
						return new SimpleDateFormat(standardDateFormat).format(arg1);
				}
				@Override
				public Object processArrayValue(Object arg0, JsonConfig arg1) {
					return arg0;
				}
			});
		}
		return jsonConfig;
	}

	//yyyyMMddhhmmss转化为yyyyMMdd
	public static String timeToDate(String timestr){
		char[] arr = timestr.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<8;i++){
			sb = sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	//yyyy-MM-dd hh:mm:ss转化为yyyyMMdd
	public static String timeToToday(String timestr){
		String[] str = timestr.split(" |-|:");
		String s = str[0]+str[1]+str[2];
		return s;
	}
	//cc-判断两个时间字符串是否为同一天
	public static boolean sameDate(String date1,String date2) throws ParseException{
//		Date d1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(date1);
//		Date d2 = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
		Date d1 = toDate(date1);
		Date d2 = toDate(date2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)&&c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)
				&&c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//cc-判断两个时间字符串是否为同一月
	public static boolean sameMonth(String date1,String date2) throws ParseException{
		Date d1 = toDate(date1);
		Date d2 = toDate(date2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)&&c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//cc-判断两个时间字符串是否为同一年
	public static boolean sameYear(String date1,String date2) throws ParseException{
		Date d1 = toDate(date1);
		Date d2 = toDate(date2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)){
			return true;
		}
		else{
			return false;
		}
	}
}
