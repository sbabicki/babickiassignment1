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
import android.view.View;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;

public class CountersActivity extends Activity {
	ArrayList<CounterModel> counters;
	ArrayAdapter<String> countersAdapter;
	ListView countersListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counters);
		
		// set up arraylist adapter for updating to screen dynamically
		countersListView = (ListView) findViewById(R.id.countersList);
		
		// respond to clicks on listview elements
		setupListViewListener();
	}
	
	protected void onStart(){
		super.onStart();
		
		// try to get old data
		try {
			counters = StoreData.readFromFile(getApplicationContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// if old data not working or DNE then make a new counters arraylist
		if(counters == null){
			counters = new ArrayList<CounterModel>();
		}
		
		countersAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1);
		
		// populate the array adapter if there is stuff to print
		for(int i = 0; i< counters.size(); i++){
			countersAdapter.add(counters.get(i).getName()+": "+counters.get(i).getCount());
		}
		countersListView.setAdapter(countersAdapter);
		
	}
	
	
	// Go to activity for a specific counter
	private void setupListViewListener() {
		countersListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(android.widget.AdapterView<?> parent,
					android.view.View view, int position, long rowId) {
				
				// go to diff activity
				switchActivity(position);
			}
		});
	}
	
	// Go to the SelectCounter activity
	public void switchActivity(int position){
		Intent intent = new Intent(this, SelectCounterActivity.class);
		
		// pass over the index of interest for the counters arraylist
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	
	// Add new counter
	public void addNewCounter(View v) throws ClassNotFoundException, IOException{
		
		// create new counter with specified name
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		CounterModel addCounter = new CounterModel(etNewItem.getText().toString());
		
		// hacky error checking. REDO IF HAVE TIME
		if(addCounter.getName().equals("")){
			addCounter.setName("(blank)");
		}
		
		// add the new counter to the counters arraylist and adapter
		counters.add(addCounter);
		countersAdapter.add(addCounter.getName()+": "+addCounter.getCount());
		
		// remove text in the add bar
		etNewItem.setText("");
		
		// save the updated arraylist of counters to file system
		StoreData.saveInFile(getApplicationContext(), counters);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counters, menu);
		return true;
	}

}
