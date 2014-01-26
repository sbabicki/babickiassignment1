package com.example.babickiassignment1;

import java.util.Calendar;
import java.util.TimeZone;

public class CounterDate {

	
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
		this.hour = localCalendar.get(Calendar.HOUR);
		this.day = localCalendar.get(Calendar.DAY_OF_MONTH);
		this.week = localCalendar.get(Calendar.WEEK_OF_YEAR);
		this.month = localCalendar.get(Calendar.MONTH);
		this.year = localCalendar.get(Calendar.YEAR);
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
