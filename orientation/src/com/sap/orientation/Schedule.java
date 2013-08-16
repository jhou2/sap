package com.sap.orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Schedule {
	
	ArrayList<Event> schedule;
	
	
	public Schedule(){
		schedule = new ArrayList<Event>();
	}
	
	public void populateSchedule(InputStream fileToRead){
	
		// Reads file
		BufferedReader input = new BufferedReader(new InputStreamReader(fileToRead));    	
		
		Event event;
		String line;
	
		try {
			line = input.readLine();
			while (line != null){
				event = new Event();
				
				event.date = line; 
				event.time = input.readLine();
				event.title = input.readLine(); 
				event.room = input.readLine(); 
				event.presenters = input.readLine(); 
				event.descr = input.readLine(); 
				
				schedule.add(event);
				line = input.readLine(); // Reads the next line (date, if there is another event)
			} // End while
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	}
	
	public ArrayList<Event> getSchedule(){
		return schedule;
	}
}
