package com.example.babickiassignment1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

public class CounterDate implements Serializable{

	
	// date information
	private int hour;
	private int day;
	private int week;
	private int month;
	private int year;
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	// constructor
	public CounterDate( ) {
		super();
		this.hour = localCalendar.get(Calendar.MILLISECOND);
		this.day = localCalendar.get(Calendar.DAY_OF_MONTH);
		this.week = localCalendar.get(Calendar.WEEK_OF_YEAR);
		this.month = localCalendar.get(Calendar.MONTH);
		this.year = localCalendar.get(Calendar.YEAR);
	}
	
	// converts 24 hour to 12 hour am/pm time
	public static String convertHours (int hour){
		if(hour>12){
			return hour-12+":00 PM";
		}
		else if(hour == 0){
			return "12:00 AM";
		}
		else {
			return hour+":00 AM";
		}
	}
	
	// returns a string representing month
	public static String getMonthString(int intMonth){
		String stringMonth;
		
		switch (intMonth) {
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

	public int getValue(){

        int hour;

        int day;

        int month;

        int year;

        hour = getHour();

        day = getDay() * 100;

        month = getMonth() * 10000;

        year = getYear() * 1000000;

        

        return hour+day+month+year;

        }
	
	
	// getters and setters for the different times. setters not really needed
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
	
	
	
}
