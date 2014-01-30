package com.example.babickiassignment1;

import java.io.FileOutputStream;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* StoreData
 * - Saves an ArrayList of counters to a file
 * - Retrieves an ArrayList of counters from a file
 * - File to save to and read from specified by FILENAME
 */
public class StoreData {
	
	private static final String FILENAME = "goat.sav";
	
	// Saves an ArrayList of Counters in a file
	public static int saveInFile(Context context, ArrayList <Counter> counters){
		try{
			// writes over old file
			FileOutputStream fileOut = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.flush();
			out.writeObject(counters);
			out.close();
			fileOut.close();
			return 1;
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
		}
		
		return 0;
	}
	
	// Returns the ArrayList from file
	public static ArrayList<Counter> readFromFile(Context context) throws IOException, ClassNotFoundException {
		
		ArrayList<Counter> counters = null;
	    
		try{
	         FileInputStream fileIn = context.openFileInput(FILENAME);
	         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	         counters = (ArrayList<Counter>) objectIn.readObject();
	         objectIn.close();
	         fileIn.close();
	         return counters;
	         
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c){
	         System.out.println("Counter class not found");
	         c.printStackTrace();
	      }
	      return null;	
	}
}
