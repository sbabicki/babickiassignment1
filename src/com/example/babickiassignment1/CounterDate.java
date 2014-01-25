package com.example.babickiassignment1;

public class CounterDate {

	// date information
	private int hour;
	private int day;
	private int week;
	private int month;
	private int year;
	
	// constructor
	public CounterDate(int hour, int day, int week, int month, int year) {
		super();
		this.hour = hour;
		this.day = day;
		this.week = week;
		this.month = month;
		this.year = year;
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
