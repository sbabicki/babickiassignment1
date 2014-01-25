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
	ArrayList<String> counters;
	ArrayAdapter<String> countersAdapter;
	ListView countersList;
	
	// store in and read from FILENAME
	private static final String FILENAME = "todo.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counters);
		
		
		countersList = (ListView) findViewById(R.id.countersList);
		counters = new ArrayList<String>();
		
		readItems();
		countersAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, counters);
		countersList.setAdapter(countersAdapter);
		setupListViewListener();
	}

	private void setupListViewListener() {
		countersList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(android.widget.AdapterView<?> parent,
					android.view.View view, int position, long rowId) {
				counters.remove(position);
				countersAdapter.notifyDataSetChanged();
				// TODO Auto-generated method stub	
			}
		});
		
	}
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	public void addTodoItem(View v){
		EditText etNewItem = (EditText)
			findViewById(R.id.etNewItem);
		String temp = etNewItem.getText().toString();
		int currentDayOfWeek = localCalendar.get(Calendar.YEAR);
		temp = temp + currentDayOfWeek;
		countersAdapter.add(temp.toString());
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
