package com.example.babickiassignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;


public class SelectCounterActivity extends CountersActivity {
	int position;
	CounterModel counter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_counter);
		
		Intent intent;
		intent = getIntent();
		position = intent.getIntExtra("position", -1);
		if (position < 0 ) {
			//SOMETHING WENT WRONG, DEAL WITH THIS
			counter = counters.get(0);
		}
		else {
			counter = counters.get(1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_counter, menu);
		return true;
	}
	
	public void printNumber(){
	Button p1_button = (Button)findViewById(R.id.count_button);
		p1_button.setText(counter.getName());
	}

}
