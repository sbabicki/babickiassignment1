package com.example.babickiassignment1;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RenameCounterActivity extends SelectCounterActivity {

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

				TextView counterName = (TextView)findViewById(R.id.rename_counter_label);
				counterName.setText(counter.getName());
				EditText oldName = (EditText) findViewById(R.id.rename_counter_text);
				oldName.setText(counter.getName());
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
	public void rename(View view){
		EditText etNewItem = (EditText) findViewById(R.id.rename_counter_text);
		counter.setName(etNewItem.getText().toString());
		replaceAndSave();
		
		Intent intent = new Intent(this, SelectCounterActivity.class);
		intent.putExtra("position", position);
		startActivity(intent); 
		
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rename_counter, menu);
		return true;
	}

}
