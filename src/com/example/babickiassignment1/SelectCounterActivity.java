package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class SelectCounterActivity extends Activity {
	int position;
	ArrayList <CounterModel> countersFromFile;
	CounterModel counter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_counter);
		
		Intent intent;
		intent = getIntent();
		position = intent.getIntExtra("position", -1);
		if (position >= 0 ) {
			//countersFromFile = StoreData.readFromFile(this);
			try {
				countersFromFile = StoreData.readFromFile(this);
				counter = countersFromFile.get(position);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				counter = new CounterModel("class");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				counter = new CounterModel("io");
			}
			
			
			
		}
		else {
			//counter = counters.get(1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_counter, menu);
		return true;
	}
	
	public void changeButtonText(View v){
		//if (counter != null){
			Button p1_button = (Button)findViewById(R.id.count_button);
			p1_button.setText(counter.getName());
		/*}
		else{
			Button p1_button = (Button)findViewById(R.id.count_button);
			p1_button.setText(":(");
		}*/
	
	}

}
