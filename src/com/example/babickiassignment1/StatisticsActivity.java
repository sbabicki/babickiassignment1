package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* StatisticsActivity
 * - Displays a listview of the counter statistics
 * - Statistics are printed by hour, day, week, and month
 * - Calculates total stats by sorting CounterDates from all counters into one list and treating as counter
 */
public class StatisticsActivity extends Activity {
	
	ListView list;
	ArrayAdapter<String> datesAdapter;
	ArrayList <CounterModel> countersFromFile;
	CounterModel counter = null;
	int position = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		Intent intent;
		intent = getIntent();
		
		// if index position sent here, use counterStatistics
		if(intent.hasExtra("position")){
			position = intent.getIntExtra("position", -1);
			counterStatistics();
		}
		
		// if index position is not given, use totalStatistics
		else{
			totalStatistics();
		}
	}
	
	// Print the statistics for a specific counter
	private void counterStatistics() {
		setup();
		getSummary();
	}
	
	// Print the statistics for all counters combined
	private void totalStatistics() {
		setup();
         
		// start totalList with first list
        ArrayList<CounterDate> totalList = countersFromFile.get(0).getDates();

        // if more than one element combine into one big arraylist
        if(countersFromFile.size() > 0){
        	
        	 // compare with each list starting with 2nd 
            for (int i = 1; i < countersFromFile.size(); i++){
            	totalList = compare(totalList, countersFromFile.get(i).getDates());
            }

            // create a new counter of total counts to do counter statistics on 
            counter = new CounterModel("total");
            counter.setDate(totalList);
        }
        
        // if only one element total = normal counter stats
        else{
        	counter = countersFromFile.get(0);
        }
         
 		getSummary();
	}

	// Set up the listview/adapter stuff and fetch counters from file
	public void setup(){
		datesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		list = (ListView)findViewById(R.id.stats);
		list.setAdapter(datesAdapter);
		
		try {
			countersFromFile = StoreData.readFromFile(getApplicationContext());
			
			// position only used for counter stats
			if(position >= 0){
				counter = countersFromFile.get(position);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	// Collect the info for the summary stats
	private void getSummary(){
		
		// if @ least 1 count get stats for counter
		if(counter.getCount()>0){
			datesAdapter.add("By Hour: \n");
			getHourSummary(counter);
			datesAdapter.add("\n");
			datesAdapter.add("By Day: \n");
			getDaySummary(counter);
			datesAdapter.add("\n");
			datesAdapter.add("By Week: \n");
			getWeekSummary(counter);
			datesAdapter.add("\n");
			datesAdapter.add("By Month: \n");
			getMonthSummary(counter);
		}
		else{
			datesAdapter.add("By Hour: \nNo Counts \n");
			datesAdapter.add("By Day: \nNo Counts \n");
			datesAdapter.add("By Week: \nNo Counts \n");
			datesAdapter.add("By Month: \nNo Counts \n");
		}
		
		// probably not necessary but i'm going to leave it in anyway, just in case
		datesAdapter.notifyDataSetChanged(); 
	}
	
	// Print to stats to screen
	private void printSummary (String dateInfo, int count){
		datesAdapter.add(dateInfo + " -- " + count + "\n");
	}
	
	// For total statistics - compare elements between 2 lists and add them all in order to the first list
	private static ArrayList<CounterDate> compare(ArrayList<CounterDate> totalList, ArrayList<CounterDate> newList){  
		
		int tIndex = 0;
		
		// if nothing to add from other list return
		if(newList.size() == 0){
			return totalList;
		}
		
		// for each element in the secondList
		for(int nIndex = 0; nIndex < newList.size(); nIndex++){
			
			// compare to each element in the firstList
			if(totalList.get(tIndex).getValue() >= newList.get(nIndex).getValue()){
				
				// if the element in the newlist belongs before the element in the totallist insert it into totallist
				totalList.add(tIndex, newList.get(nIndex));
			 }
			
			// the the element in the second list is larger than the first list
			else{
				
				// if we reach the end of the first list
				if(tIndex == totalList.size()-1){
					
					// add the rest of the second list to the first list
					for(int s = nIndex; s < newList.size(); s++){
						totalList.add(newList.get(s));
					}
					// we are done
					return totalList;
				}
				
				// if not at the end of the first list then move to the next element of the first list
				else{
					tIndex++;
					
					// we need to offset nIndex++ since we still have to find a place for the current
					nIndex--;
				}
			}
		}
		
		// return the first list with the new members added to it
		return totalList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Get individual statistics for different CounterDate attributes
	// there's got to be a better way of doing this...
	private void getHourSummary(CounterModel counter){
		
		// if only one entry: 
		if(counter.getCount() == 1){
			printSummary(counter.getDates().get(0).getMonthString()
					+" "+ counter.getDates().get(0).getDay()
					+" "+ counter.getDates().get(0).convertHours(), 
					1);
			return;
		}
		
		// if at least 2 entries:
		int hourCount = 1;
		for(int i = 0; i < counter.getCount() - 1; i++){
			
			// if dates of two count entries match, increase the count (for elements of the same category)
			if(counter.getDates().get(i).getHour() == counter.getDates().get(i+1).getHour()){
				hourCount ++;
			}
			
			// if the two entries don't match we can print that section and move on to the next
			else{
				printSummary(counter.getDates().get(i).getMonthString()
						+" "+ counter.getDates().get(i).getDay()
						+" "+ counter.getDates().get(i).convertHours(), 
						hourCount);
				hourCount = 1;
			}
		}
		
		// print the last section manually (b/c of for loop design)
		printSummary(counter.getDates().get(counter.getCount()-1).getMonthString()
				+" "+ counter.getDates().get(counter.getCount()-1).getDay()
				+" "+ counter.getDates().get(counter.getCount()-1).convertHours(),
				hourCount);
	}
	
	// The rest of these methods are the same as the one above, except they send different strings to printSummary
	// for each category. 
	private void getDaySummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(counter.getDates().get(0).getMonthString()
					+" "+ counter.getDates().get(0).getDay(),
					1);
			return;
		}
		int dayCount = 1;
		for(int i = 0; i < counter.getCount() - 1; i++){
			if(counter.getDates().get(i).getDay() == counter.getDates().get(i+1).getDay()){
				dayCount ++;
			}
			else{
				printSummary(counter.getDates().get(i).getMonthString()
						+" "+ counter.getDates().get(i).getDay(), 
						dayCount);
				dayCount = 1;
			}
		}
		printSummary(counter.getDates().get(counter.getCount()-1).getMonthString()
				+" "+ counter.getDates().get(counter.getCount()-1).getDay(),
				dayCount);
	}
	private void getWeekSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary("Week of "+ counter.getDates().get(0).getMonthString() 
					+" "+ counter.getDates().get(0).getDayOfWeek(),
					1);
			return;
		}
		int weekCount = 1;
		for(int i = 0; i < counter.getCount() - 1; i++){
			if(counter.getDates().get(i).getWeek() == counter.getDates().get(i+1).getWeek()){
				weekCount ++;
			}
			else{
				printSummary("Week of "+ counter.getDates().get(i).getMonthString() 
						+" "+ counter.getDates().get(i).getDayOfWeek(), weekCount);
				weekCount = 1;
			}
		}
		printSummary("Week of "+ counter.getDates().get(counter.getCount()-1).getMonthString() 
				+" "+ counter.getDates().get(counter.getCount()-1).getDayOfWeek(), 
				weekCount);
	}
	private void getMonthSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(counter.getDates().get(0).getMonthString(), 1);
			return;
		}
		int monthCount = 1;
		for(int i = 0; i < counter.getCount() - 1; i++){
			if(counter.getDates().get(i).getMonth() == counter.getDates().get(i+1).getMonth()){
				monthCount ++;
			}
			else{
				printSummary(counter.getDates().get(i).getMonthString(), monthCount);
				monthCount = 1;
			}
		}
		printSummary(counter.getDates().get(counter.getCount()-1).getMonthString(), monthCount);
	}

	// Menu only provides a link to homepage (CountersActivity)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}
	
	// Handle item selection
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.home_from_stats:
	    		returnHome();
	    		return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// Return to CountersActivity when home option on menu is clicked
	public void returnHome(){
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}

}
