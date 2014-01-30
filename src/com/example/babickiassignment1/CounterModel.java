package com.example.babickiassignment1;

import java.io.Serializable;
import java.util.ArrayList;

/* CounterModel
 * - Object to store counters -> stores name & date info
 * - Stores date information in an arraylist of CounterDates
 * - Each time a CounterDate is added to the arraylist the count is increased
 * - A string must be passed to the constructor to create a new CounterModel 
 */
public class CounterModel implements Serializable{

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
	
	// add a CounterDate to the arraylist
	public void addCount(){
		datesList.add(new CounterDate());
	}
	
	// the size of the array = # counts
	public int getCount(){
		return datesList.size();
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<CounterDate> getDates() {
		return datesList;
	}

	public void setDate(ArrayList<CounterDate> date) {
		this.datesList = date;
	}

}
