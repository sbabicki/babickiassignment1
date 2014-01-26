package com.example.babickiassignment1;


import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import java.io.BufferedReader;



//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.TimeZone;



//import android.content.Context;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;

public class CountersActivity extends Activity {
	ArrayList<CounterModel> counters;
	ArrayAdapter<String> countersAdapter;
	ListView countersListView;
	
	// store in and read from FILENAME
	private static final String FILENAME = "file.sav";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counters);
		
		
		countersListView = (ListView) findViewById(R.id.countersList);
		counters = new ArrayList<CounterModel>();
		
		readItems();
		countersAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1);
		countersListView.setAdapter(countersAdapter);
		setupListViewListener();
		setupLongListViewListener();
	}

	private void setupListViewListener() {
		countersListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(android.widget.AdapterView<?> parent,
					android.view.View view, int position, long rowId) {
				
				sendMessage(position);
				
				//counters.add();
				//countersAdapter.add(""+ counters.get(position).getDate().size());;
				
				
				//counters.get(position).addCount();
				//countersAdapter.notifyDataSetChanged();
			
				// TODO Auto-generated method stub	
			}
		});
	}
	
	public void sendMessage(int position){
		Intent intent = new Intent(this, SelectCounterActivity.class);
		
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	private void setupLongListViewListener() {
		countersListView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(android.widget.AdapterView<?> parent,
					android.view.View view, int position, long rowId) {
				
				countersAdapter.remove(counters.get(position).getName());;
				counters.remove(position);
				countersAdapter.notifyDataSetChanged();
				return true;
				// TODO Auto-generated method stub	
			}
		});
		
	}
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	public void addTodoItem(View v) throws ClassNotFoundException, IOException{
		EditText etNewItem = (EditText)
			findViewById(R.id.etNewItem);
		
		CounterModel addName = new CounterModel(etNewItem.getText().toString());
		counters.add(addName);
		//int currentDayOfWeek = localCalendar.get(Calendar.YEAR);
		//temp = temp + currentDayOfWeek;
		countersAdapter.add(addName.getName());
		
		// remove text in the add 
		etNewItem.setText("");
		int last = counters.size() - 1;
		//countersAdapter.add(""+last);
		int sa = StoreData.saveInFile(this, counters);
		//countersAdapter.add(""+sa);
		ArrayList<CounterModel> test = StoreData.readFromFile(this);
		//counters.add(test.get(0));
		//int currentDayOfWeek = localCalendar.get(Calendar.YEAR);
		//temp = temp + currentDayOfWeek;
		if(test == null){
			countersAdapter.add(":(");
		}
		else{
		countersAdapter.add(test.get(last).getName().toString());
		//saveItems(items.toString());
		}
	}

	private void readItems() {
		// TODO Auto-generated method stub
		
	}
/*	
	public CounterModel getCounter (int position){
		return counters.get(position);
	}
	//MIGHT DELETE LATER
	public void setCounter(int position){
		counters.get(position).addCount();
	}
*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}