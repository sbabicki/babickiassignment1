package com.example.babickiassignment1;

public class CounterModel {

	// name of counter
	private String name;
	
	// date info for counter
	private CounterDate date;
	
	// count 
	private int count;

	// constructor
	public CounterModel(String name, CounterDate date, int count) {
		super();
		this.name = name;
		this.date = date;
		this.count = count;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CounterDate getDate() {
		return date;
	}

	public void setDate(CounterDate date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	

}
