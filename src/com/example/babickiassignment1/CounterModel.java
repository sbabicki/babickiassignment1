package com.example.babickiassignment1;

import java.util.ArrayList;

public class CounterModel {

	// name of counter
	private String name;
	
	// date info for counter
	private ArrayList<CounterDate> date;

	// constructor
	public CounterModel(String name, ArrayList<CounterDate> date) {
		super();
		this.name = name;
		this.date = date;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<CounterDate> getDate() {
		return date;
	}

	public void setDate(ArrayList<CounterDate> date) {
		this.date = date;
	}

}
