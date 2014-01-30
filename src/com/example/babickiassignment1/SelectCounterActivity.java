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

/* SelectCounterActivity
 * - Displays the count of an individual counter
 * - Allows the user to update the count of the counter
 * - Continually sorts counters by finding correct ordering within list of counters whenever count++
 * - Contains menu with options to edit the counter and view stats
 * 		Menu Items:
 * 		- home -> starts CountersActivity
 * 		- remove -> deletes the counter from the counters arraylist and saves change
 * 		- reset -> removes the CounterDate info from the counter
 * 		- rename -> handled by RenameActivity
 * 		- counter statistics -> handled by StatisticsActivity
 * 		- total statistics -> handled by StatisticsActivity
 */
public class SelectCounterActivity extends Activity {
	
	// Index of counter of interest in the arraylist of counters
	int position;
	ArrayList <CounterModel> countersFromFile;
	CounterModel counter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_counter);
		
		// retrieve the position of the counter of interest
		Intent intent;
		intent = getIntent();
		position = intent.getIntExtra("position", -1);
		
		// if the position is valid try to read from the file to get the counters arraylist
		if (position >= 0) {
			
			// counter = counter of interest
			try {
				countersFromFile = StoreData.readFromFile(getApplicationContext());
				counter = countersFromFile.get(position);

				// display the counter name on the screen
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

	// The select_counter menu has all the options for editing an existing counter
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_counter, menu);
		return true;
	}
	
	// Handle item selection
	public boolean onOptionsItemSelected(MenuItem item) {
	    
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
	
	// Return to homepage (CountersActivity)
	private void returnHome(){
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}
	
	// Delete a counter
	private void removeCounter(){
		
		// remove counter from arraylist and save the updated list to file
		countersFromFile.remove(position);
		if(StoreData.saveInFile(getApplicationContext(), countersFromFile) == 0){
			addButtonMessage("ERROR SAVING FILE:(", false);
		}
		
		// return to CountersActivity once counter is deleted
		Intent intent = new Intent(this, CountersActivity.class);
		startActivity(intent); 
	}
	
	// Reset a counter
	private void resetCounter(){
		
		// remove all the CounterDate data for the counter = set count to zero
		counter.getDates().clear();
		addButtonMessage(counter.getName() + " HAS BEEN RESET \n Tap the screen to increment the count", true);
		
		// now that count is zero, place at end of the list
		countersFromFile.remove(position);
		countersFromFile.add(counter);
		
		//change index of counter to it's new position
		position = countersFromFile.size() - 1;
		
		// save file
		StoreData.saveInFile(getApplicationContext(), countersFromFile);
	}
	
	// Rename a counter - dealt with by RenameActivity class
	private void renameCounter(){
		
		// go to RenameActivity
		Intent intent = new Intent(this, RenameCounterActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	// Start StatisticsActivity with position extra
	private void counterStats(){
		
		Intent intent = new Intent(this, StatisticsActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
	}
	
	// Start StatisticsActivity with no extras
	private void totalStats(){
		
		Intent intent = new Intent(this, StatisticsActivity.class);
		startActivity(intent);
	}
	
	// Called when button is pressed. Increase the counters count by 1
	public void changeButtonText(View v){
		
		// if the read or position worked properly add count
		if (counter != null){
			
			// count ++
			counter.addCount();
			
			sort();
			
			// update the button text
			addButtonMessage("Tap the screen to increment the count", true);
			replaceAndSave();
		}
		else{
			addButtonMessage("ERROR READING FILE:(", false);
		}
	}
	
	// Sort in descending order, assumes rest of list is in order and count only increases
	// NOTE: sorting only works if all stored data is created by this program
	private void sort(){
		for(int i = 0; i < position; i ++){
			if(counter.getCount() > countersFromFile.get(i).getCount()){
							
				// if we are in the wrong position swap with neighbor
				swap(countersFromFile.get(i), i);
			}
		}	
	}
	
	// Swaps positions - for sorting
	private void swap(CounterModel oldCounter, int newPosition){
		countersFromFile.set(position, oldCounter);
		countersFromFile.set(newPosition, counter);
		position = newPosition;
	}
	
	// Replaces the old instance of the counter with the new one and saves to file
	private void replaceAndSave(){
		
		// replace the old counter with the new one 
		countersFromFile.set(position, counter);
		if(StoreData.saveInFile(getApplicationContext(), countersFromFile) == 0){
			addButtonMessage("ERROR SAVING FILE:(", false);
		}
	}
	
	// Set the count button to display a message and possibly the count (based on boolean count)
	private void addButtonMessage (String text, Boolean count){
		Button countButton = (Button)findViewById(R.id.count_button);
		if(count == true){
			countButton.setText(text + "\n" + counter.getCount());
		}
		else{
			countButton.setText(text);
		}
	}
}
