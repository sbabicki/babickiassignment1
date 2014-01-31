package com.example.babickiassignment1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/* CounterDate
 * - Stores the date information for calculating counter statistics
 * - Has helper methods to convert format of date information
 */
public class CounterDate implements Serializable{

	// Date information
	private int hour;
	private int day;
	private int week;
	private int month;
	private int year;
	private int dayOfYear;
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	// Constructor
	public CounterDate() {
		super();
		this.hour = localCalendar.get(Calendar.HOUR_OF_DAY);
		this.day = localCalendar.get(Calendar.DAY_OF_MONTH);
		this.week = localCalendar.get(Calendar.WEEK_OF_YEAR);
		this.month = localCalendar.get(Calendar.MONTH);
		this.year = localCalendar.get(Calendar.YEAR);
		this.dayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);
	}
	
	// Converts 24 hour to 12 hour am/pm time
	public String convertHours (){
	
		if(hour > 12){
			return getHour() - 12 + ":00 PM";
		}
		else if(hour == 12){
			return "12:00 PM";
		}
		else if(hour == 0){
			return "12:00 AM";
		}
		else {
			return getHour() + ":00 AM";
		}
	}
	
	// Returns a string representing month
	public String getMonthString(){
		
		String stringMonth;
		
		switch (month) {
			case 0: stringMonth = "January";
			break;
			case 1: stringMonth = "February";
			break;
			case 2: stringMonth = "March";
			break;
			case 3: stringMonth = "April";
			break;
			case 4: stringMonth = "May";
			break;
			case 5: stringMonth = "June";
			break;
			case 6: stringMonth = "July";
			break;
			case 7: stringMonth = "August";
			break;
			case 8: stringMonth = "September";
			break;
			case 9: stringMonth = "October";
			break;
			case 10: stringMonth = "November";
			break;
			case 11: stringMonth = "December";
			break;
			default: stringMonth = "Invalid Month";
			break;
			}
		
			return stringMonth;
	}
	
	// Returns the first day of a week. eg: 28
	public int getDayOfWeek(){
		
		int dayOffset;
		int newDay = getDayOfYear();
		
		// first week starts with 1
		if(newDay <= 7){
			return 1;
		}
		
		dayOffset = newDay - ((week-1)*7);
		newDay = newDay - dayOffset;
		
		return newDay;
	}
	
	// Creates a value to represent a date. larger value = later date
	public int getValue(){

        int hourValue;
        int dayValue;
        int monthValue;
        int yearValue;

        hourValue = getHour();
        dayValue = getDay() * 100;
        monthValue = getMonth() * 10000;
        yearValue = getYear() * 1000000;
        
        return hourValue + dayValue + monthValue + yearValue;   
	}
	
	// Getters and setters for the different times. Setters not really needed
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getDayOfYear() {
		return dayOfYear;
	}

	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}
}
