package com.example.babickiassignment1;

import java.io.Serializable;
import java.util.ArrayList;

public class CounterModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// name of counter
	private String name;
	
	// date info for counter
	private ArrayList<CounterDate> datesList;
	
	// constructor
	public CounterModel(String name) {
		super();
		this.name = name;
		this.datesList = new ArrayList<CounterDate>();
	}
	
	public void addCount(){
		datesList.add(new CounterDate());
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<CounterDate> getDate() {
		return datesList;
	}

	public void setDate(ArrayList<CounterDate> date) {
		this.datesList = date;
	}

}
