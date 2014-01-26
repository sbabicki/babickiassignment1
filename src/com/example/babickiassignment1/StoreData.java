package com.example.babickiassignment1;

import java.io.FileOutputStream;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class StoreData {
	private static final String FILENAME = "mike.sav";

	public StoreData() {
		// TODO Auto-generated constructor stub
	}
	
	public static int saveInFile(Context context, ArrayList <CounterModel> counters){
		try{
			
			FileOutputStream fileOut = context.openFileOutput(FILENAME, Context.MODE_APPEND);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(counters);
			out.close();
			fileOut.close();
			return 1;
			         /*
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_APPEND);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(newCounter);
		    oos.close();
		    fos.close();*/
		} catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 2;
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 3;
		}
	}
	
	public static ArrayList<CounterModel> readFromFile(Context context) 
			throws IOException, ClassNotFoundException {
		/*FileInputStream fis = context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		CounterModel object = (CounterModel) ois.readObject();
		return object;*/
		ArrayList<CounterModel> e = null;
	      try
	      {
	         FileInputStream fileIn = context.openFileInput(FILENAME);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (ArrayList<CounterModel>) in.readObject();
	         in.close();
	         fileIn.close();
	         return e;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("CounterModel class not found");
	         c.printStackTrace();
	         return null;
	      }
	      
	      
		
	}
	
	/*
	 private void saveInFile(String text, Date date) {
         try {
                 FileOutputStream fos = openFileOutput("askdjfs",
                                 Context.MODE_APPEND);
                 fos.write(new String(date.toString() + " | " + text)
                                 .getBytes());
                 fos.close();
         } catch (FileNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
 }
	
	public static void writeObject(Context context, String key, Object object) throws IOException {
	      FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(object);
	      oos.close();
	      fos.close();
	   }
	 
	   public static Object readObject(Context context, String key) throws IOException,
	         ClassNotFoundException {
	      FileInputStream fis = context.openFileInput(key);
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      Object object = ois.readObject();
	      return object;
	   } */

}
