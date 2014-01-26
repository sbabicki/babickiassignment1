package com.example.babickiassignment1;


import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;

public class CountersActivity extends Activity {
	ArrayList<CounterModel> counters;
	ArrayAdapter<String> countersAdapter;
	ListView countersList;
	
	// store in and read from FILENAME
	private static final String FILENAME = "todo.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counters);
		
		
		countersList = (ListView) findViewById(R.id.countersList);
		counters = new ArrayList<CounterModel>();
		
		readItems();
		countersAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1);
		countersList.setAdapter(countersAdapter);
		setupListViewListener();
		setupLongListViewListener();
	}

	private void setupListViewListener() {
		countersList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(android.widget.AdapterView<?> parent,
					android.view.View view, int position, long rowId) {
				counters.get(position).addCount();
				//counters.add();
				//countersAdapter.add(""+ counters.get(position).getDate().size());;
				
				countersAdapter.notifyDataSetChanged();
			
				// TODO Auto-generated method stub	
			}
		});
	}
	
	private void setupLongListViewListener() {
		countersList.setOnItemLongClickListener(new OnItemLongClickListener(){
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
	
	public void addTodoItem(View v){
		EditText etNewItem = (EditText)
			findViewById(R.id.etNewItem);
		
		CounterModel addName = new CounterModel(etNewItem.getText().toString());
		counters.add(addName);
		//int currentDayOfWeek = localCalendar.get(Calendar.YEAR);
		//temp = temp + currentDayOfWeek;
		countersAdapter.add(addName.getName());
		
		// remove text in the add 
		etNewItem.setText("");
		//saveItems(items.toString());
	}

	private void readItems() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
