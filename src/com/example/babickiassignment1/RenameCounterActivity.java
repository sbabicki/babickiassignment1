package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* RenameCounterActivity
 * - Allows user to input a name to replace an existing counter name
 * - Only accessed through SelectCounterActivity menu
 * - Goes back to SelectCounterActivity when complete
 */
public class RenameCounterActivity extends Activity {

	// Index of counter of interest in the arraylist of counters
	int position;
	ArrayList <CounterModel> countersFromFile;
	CounterModel counter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rename_counter);
		
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
				
				// print old name at top of screen
				TextView counterName = (TextView)findViewById(R.id.rename_counter_label);
				counterName.setText(counter.getName());
				
				// put old name in bar to edit
				EditText oldName = (EditText) findViewById(R.id.rename_counter_text);
				oldName.setText(counter.getName());
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();	
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	// Replaces old name of counter with new one. Called when Rename button is clicked.
	public void rename(View view){
		
		// gets new name from user input
		EditText newName = (EditText) findViewById(R.id.rename_counter_text);
		counter.setName(newName.getText().toString());
		replaceAndSave();
		
		// return to counter specific page when finished with the rename process
		Intent intent = new Intent(this, SelectCounterActivity.class);
		intent.putExtra("position", position);
		startActivity(intent); 
	}
	
	// Replaces the old instance of the counter with the new one and saves to file
	private void replaceAndSave (){
		
		// replace the old counter with the new one 
		countersFromFile.set(position, counter);
		StoreData.saveInFile(getApplicationContext(), countersFromFile);
	}
	
	// No menu items
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.rename_counter, menu);
		return true;
	}
}
