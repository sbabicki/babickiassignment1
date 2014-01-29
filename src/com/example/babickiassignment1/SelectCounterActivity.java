package com.example.babickiassignment1;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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

				TextView counterName = (TextView)findViewById(R.id.counter_name);
				counterName.setText(counter.getName());
				
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
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.home:
	    		returnHome();
	    		return true;
	        case R.id.remove_counter:
	            removeCounter();
	            return true;
	        case R.id.reset_counter:
	            resetCounter();
	            return true;
	        case R.id.rename_counter:
	            renameCounter();
	            return true;
	        case R.id.counter_stats:
	            counterStats();
	            return true;
	        case R.id.total_stats:
	            totalStats();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void returnHome(){
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}
	
	public void removeCounter(){
		countersFromFile.remove(position);
		if(StoreData.saveInFile(getApplicationContext(), countersFromFile) == 0){
			addButtonMessage("ERROR SAVING FILE:(", false);
		}
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}
	
	public void resetCounter(){
		counter.getDates().clear();
		addButtonMessage(counter.getName() + " HAS BEEN RESET \n Tap the screen to increment the count", true);
		replaceAndSave();
	}
	
	public void renameCounter(){
		Intent intent = new Intent(this, RenameCounterActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	// start activity with position extra
	public void counterStats(){
		Intent intent = new Intent(this, StatisticsActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	// start activity with no extras
	public void totalStats(){
		Intent intent = new Intent(this, StatisticsActivity.class);
		startActivity(intent);
	}
	
	// Increase the count 
	public void changeButtonText(View v) throws ClassNotFoundException, IOException{
		
		// if the read or position worked properly add count
		if (counter != null){
			
			// count ++
			counter.addCount();
			
			// sort
			for(int i = 0; i < position; i ++){
				if(counter.getCount() <= countersFromFile.get(i).getCount()){
					// we are in the right position
				}
				else{
					swap(countersFromFile.get(i), i);
				}
			}
			
			// update the button text
			addButtonMessage("Tap the screen to increment the count", true);
			replaceAndSave();
			
		}
		else{
			addButtonMessage("ERROR READING FILE:(", false);
		}
	}
	
	// swaps positions - for sorting
	public void swap(CounterModel oldCounter, int newPosition){
		countersFromFile.set(position, oldCounter);
		countersFromFile.set(newPosition, counter);
		position = newPosition;
	}
	
	// replaces the old instance of the counter with the new one and saves to file
	public void replaceAndSave (){
		
		// replace the old counter with the new one
		countersFromFile.set(position, counter);
		if(StoreData.saveInFile(getApplicationContext(), countersFromFile) == 0){
			addButtonMessage("ERROR SAVING FILE:(", false);
		}
	}
	
	// Set the count button to display a message and possibly the count
	public void addButtonMessage (String text, Boolean count){
		Button countButton = (Button)findViewById(R.id.count_button);
		if(count == true){
			countButton.setText(text + "\n" + counter.getCount());
		}
		else{
			countButton.setText(text);
		}
	}
}
