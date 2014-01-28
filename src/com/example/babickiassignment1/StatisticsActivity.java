package com.example.babickiassignment1;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StatisticsActivity extends SelectCounterActivity {
	int position;
	ListView list;
	ArrayAdapter<String> datesAdapter;
	
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
			
			int hourCount = 1;
			
			datesAdapter.add("By Hour: \n");
			for(int i = 0; i< counter.getCount()-1; i++){
				if(counter.getDate().get(i).getHour() == counter.getDate().get(i+1).getHour()){
					hourCount ++;
				}
				else{
					datesAdapter.add(""+ counter.getDate().get(i).getHour() +": "+ hourCount+"\n");
					hourCount = 1;
				}
			}
			datesAdapter.add(""+ counter.getDate().get(counter.getCount()-1).getHour() +": "+ hourCount+"\n");
			
			
			
		}
		
		
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
