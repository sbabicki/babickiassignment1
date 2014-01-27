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
		
		// retrieve the position of the counter of interest
		Intent intent;
		intent = getIntent();
		position = intent.getIntExtra("position", -1);
		
		// if the position is valid try to read from the file to get the counters arraylist
		if (position >= 0 ) {
			
			// counter = counter of interest
			try {
				countersFromFile = StoreData.readFromFile(getApplicationContext());
				counter = countersFromFile.get(position);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addButtonMessage("Tap the screen to increment the count", true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_counter, menu);
		return true;
	}
	
	// Increase the count 
	public void changeButtonText(View v) throws ClassNotFoundException, IOException{
		
		// if the read or position worked properly add count
		if (counter != null){
			
			// count ++
			counter.addCount();
			
			// update the button text
			addButtonMessage("Tap the screen to increment the count", true);
			
			// replace the old counter with the new one
			countersFromFile.set(position, counter);
			if(StoreData.saveInFile(getApplicationContext(), countersFromFile) == 0){
				addButtonMessage("ERROR SAVING FILE:(", false);
			}
		}
		else{
			addButtonMessage("ERROR READING FILE:(", false);
		}
	}
	
	// Set the count button to display a message and possibly the count
	public void addButtonMessage (String text, Boolean count){
		Button countButton = (Button)findViewById(R.id.count_button);
		if(count == true){
			countButton.setText("Counter Name: " + counter.getName() + "\n\n" + text + "\n" + counter.getCount());
		}
		else{
			countButton.setText(text);
		}
	}
}
