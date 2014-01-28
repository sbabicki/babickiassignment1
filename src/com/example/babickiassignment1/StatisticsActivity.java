package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
	}
	
	// there's got to be a better way of doing this...
	private void getHourSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDate().get(0).getMonth())
					//+" "+counter.getDate().get(0).getWeek()
					+" "+counter.getDate().get(0).getDay()
					+" "+ CounterDate.convertHours(counter.getDate().get(0).getHour()), 
					1);
			return;
		}
		int hourCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDate().get(i).getHour() == counter.getDate().get(i+1).getHour()){
				hourCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDate().get(i).getMonth())
						//+" "+counter.getDate().get(i).getWeek()
						+" "+counter.getDate().get(i).getDay()
						+" "+ CounterDate.convertHours(counter.getDate().get(i).getHour()), 
						hourCount);
				hourCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDate().get(counter.getCount()-1).getMonth())
				//+" "+counter.getDate().get(counter.getCount()-1).getWeek()+counter.getDate().get(counter.getCount()-1).getWeek()
				+" "+counter.getDate().get(counter.getCount()-1).getDay()
				+" "+ CounterDate.convertHours(counter.getDate().get(counter.getCount()-1).getHour()),
				hourCount);
	}
	private void getDaySummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDate().get(0).getMonth())
					//+" "+counter.getDate().get(0).getWeek()
					+" "+counter.getDate().get(0).getDay(),
					1);
			return;
		}
		int dayCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDate().get(i).getDay() == counter.getDate().get(i+1).getDay()){
				dayCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDate().get(i).getMonth())
						//+" "+counter.getDate().get(i).getWeek()
						+" "+counter.getDate().get(i).getDay(), 
						dayCount);
				dayCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDate().get(counter.getCount()-1).getMonth())
				//+" "+counter.getDate().get(counter.getCount()-1).getWeek()+counter.getDate().get(counter.getCount()-1).getWeek()
				+" "+counter.getDate().get(counter.getCount()-1).getDay(),
				dayCount);
	}
	private void getWeekSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(//CounterDate.getMonthString(counter.getDate().get(0).getMonth())
					"Week "+counter.getDate().get(0).getWeek(),
					1);
			return;
		}
		int weekCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDate().get(i).getWeek() == counter.getDate().get(i+1).getWeek()){
				weekCount ++;
			}
			else{
				printSummary(//CounterDate.getMonthString(counter.getDate().get(i).getMonth())
						"Week "+counter.getDate().get(i).getWeek(), weekCount);
				weekCount = 1;
			}
		}
		printSummary(//CounterDate.getMonthString(counter.getDate().get(counter.getCount()-1).getMonth())
				"Week "+counter.getDate().get(counter.getCount()-1).getWeek()+counter.getDate().get(counter.getCount()-1).getWeek(), 
				weekCount);
	}
	private void getMonthSummary(CounterModel counter){
		
		if(counter.getCount() == 1){
			printSummary(CounterDate.getMonthString(counter.getDate().get(0).getMonth()), 1);
			return;
		}
		int monthCount = 1;
		for(int i = 0; i< counter.getCount()-1; i++){
			if(counter.getDate().get(i).getMonth() == counter.getDate().get(i+1).getMonth()){
				monthCount ++;
			}
			else{
				printSummary(CounterDate.getMonthString(counter.getDate().get(i).getMonth()), monthCount);
				monthCount = 1;
			}
		}
		printSummary(CounterDate.getMonthString(counter.getDate().get(counter.getCount()-1).getMonth()), monthCount);
	}
	
	private void printSummary (String dateInfo, int count){
		datesAdapter.add(dateInfo + " -- " + count + "\n");
	}
	
	
	


	private void totalStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}

}
