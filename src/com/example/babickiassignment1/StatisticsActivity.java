package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StatisticsActivity extends Activity {
	int position;
	ListView list;
	ArrayAdapter<String> datesAdapter;
	ArrayList <CounterModel> countersFromFile;
	CounterModel counter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		Intent intent;
		intent = getIntent();
		if(intent.hasExtra("position")){
			position = intent.getIntExtra("position", -1);
			counterStatistics();
		}
		else{
			totalStatistics();
		}
	}
	
	private void counterStatistics() {
		setup();
		getSummary();
	}
	
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

            counter = new CounterModel("total");
            counter.setDate(totalList);
        }
        
        // if only one element total = normal counter stats
        else{
        	counter = countersFromFile.get(0);
        }
         
 		getSummary();
	}

	
	public void setup(){
		datesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		list = (ListView)findViewById(R.id.stats);
		list.setAdapter(datesAdapter);
		
		try {
			countersFromFile = StoreData.readFromFile(getApplicationContext());
			counter = countersFromFile.get(position);
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
	
	// collect the info for the summary
	private void getSummary(){
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
	}
	
	// print to stats to screen
	private void printSummary (String dateInfo, int count){
		datesAdapter.add(dateInfo + " -- " + count + "\n");
	}
	

	// for total statistics - compare elements between 2 lists and add them all in order to the first list
	private static ArrayList<CounterDate> compare(ArrayList<CounterDate> totalList, ArrayList<CounterDate> newList){  
		int x = 0;
		
		// if nothing to add from other list return
		if(newList.size() == 0){
			return totalList;
		}
		
		// for each element in the secondList
		for(int j = 0; j < newList.size(); j++){
			
			// compare to each element in the firstList
			if(totalList.get(x).getValue() >= newList.get(j).getValue()){
				
				// if the element in the second list belongs before the element in the first list insert it into first list
				totalList.add(x, newList.get(j));
			 }
			
			// the the element in the second list is larger than the first list
			else{
				
				// if we reach the end of the first list
				if(x+1 >= totalList.size()){
					
					// add the rest of the second list to the first list
					for(int s = j; s < newList.size(); s++){
						totalList.add(newList.get(s));
					}
					// we are done
					return totalList;
				}
				
				// if not at the end of the first list then move to the next element of the first list
				else{
					x++;
				}
			}
		}
		
		// return the first list with the new members added to it
		return totalList;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// there's got to be a better way of doing this...
	private void getHourSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDates().get(0).getMonth())
					//+" "+counter.getDate().get(0).getWeek()
					+" "+counter.getDates().get(0).getDay()
					+" "+ CounterDate.convertHours(counter.getDates().get(0).getHour()), 
					1);
			return;
		}
		int hourCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDates().get(i).getHour() == counter.getDates().get(i+1).getHour()){
				hourCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDates().get(i).getMonth())
						//+" "+counter.getDate().get(i).getWeek()
						+" "+counter.getDates().get(i).getDay()
						+" "+ CounterDate.convertHours(counter.getDates().get(i).getHour()), 
						hourCount);
				hourCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDates().get(counter.getCount()-1).getMonth())
				//+" "+counter.getDate().get(counter.getCount()-1).getWeek()+counter.getDate().get(counter.getCount()-1).getWeek()
				+" "+counter.getDates().get(counter.getCount()-1).getDay()
				+" "+ CounterDate.convertHours(counter.getDates().get(counter.getCount()-1).getHour()),
				hourCount);
	}
	private void getDaySummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDates().get(0).getMonth())
					//+" "+counter.getDate().get(0).getWeek()
					+" "+counter.getDates().get(0).getDay(),
					1);
			return;
		}
		int dayCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDates().get(i).getDay() == counter.getDates().get(i+1).getDay()){
				dayCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDates().get(i).getMonth())
						//+" "+counter.getDate().get(i).getWeek()
						+" "+counter.getDates().get(i).getDay(), 
						dayCount);
				dayCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDates().get(counter.getCount()-1).getMonth())
				//+" "+counter.getDate().get(counter.getCount()-1).getWeek()+counter.getDate().get(counter.getCount()-1).getWeek()
				+" "+counter.getDates().get(counter.getCount()-1).getDay(),
				dayCount);
	}
	private void getWeekSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(//CounterDate.getMonthString(counter.getDate().get(0).getMonth())
					"Week "+counter.getDates().get(0).getWeek(),
					1);
			return;
		}
		int weekCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDates().get(i).getWeek() == counter.getDates().get(i+1).getWeek()){
				weekCount ++;
			}
			else{
				printSummary(//CounterDate.getMonthString(counter.getDate().get(i).getMonth())
						"Week "+counter.getDates().get(i).getWeek(), weekCount);
				weekCount = 1;
			}
		}
		printSummary(//CounterDate.getMonthString(counter.getDate().get(counter.getCount()-1).getMonth())
				"Week "+counter.getDates().get(counter.getCount()-1).getWeek()+counter.getDates().get(counter.getCount()-1).getWeek(), 
				weekCount);
	}
	private void getMonthSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDates().get(0).getMonth()), 1);
			return;
		}
		int monthCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDates().get(i).getMonth() == counter.getDates().get(i+1).getMonth()){
				monthCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDates().get(i).getMonth()), monthCount);
				monthCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDates().get(counter.getCount()-1).getMonth()), monthCount);
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.home_from_stats:
	    		returnHome();
	    		return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void returnHome(){
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}

}
