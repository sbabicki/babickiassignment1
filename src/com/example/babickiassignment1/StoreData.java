package com.example.babickiassignment1;

import java.io.FileOutputStream;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class StoreData {
	private static final String FILENAME = "mike.sav";

	public StoreData() {
		// TODO Auto-generated constructor stub
	}
	
	// saves an arraylist in file
	public static int saveInFile(Context context, ArrayList <CounterModel> counters){
		int ret = 1;
		try{
			FileOutputStream fileOut = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.flush();
			
			out.writeObject(counters);
			//ret = 0;
			out.close();
			fileOut.close();
			
			return ret;
			
		} catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 2;
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
		}
	}
	
	// returns the arraylist from file
	public static ArrayList<CounterModel> readFromFile(Context context) 
			throws IOException, ClassNotFoundException {
		ArrayList<CounterModel> counters = null;
	    try
	    {
	         FileInputStream fileIn = context.openFileInput(FILENAME);
	         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	         counters = (ArrayList<CounterModel>) objectIn.readObject();
	         objectIn.close();
	         fileIn.close();
	         return counters;
	         
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("CounterModel class not found");
	         c.printStackTrace();
	      }
	      return null;	
	}
}
