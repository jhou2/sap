package com.sap.orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


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
			input.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	}
	
	public ArrayList<Event> getSchedule(){
		return schedule;
	}
	
	// Returns a list of the dates in the schedule
	public ArrayList<String> getScheduleDates(){
		ArrayList<String> dates = new ArrayList<String>();
		
		for (Event event : schedule){
			String date = event.getDate();
			if(!dates.contains(date)){
				dates.add(date);
			}
		}
		return dates;
			
	}
	
	//Return a Map, where the keys are dates, and the values are list of events
	public Map<String,ArrayList<Event>> getMapDateEvents(){
		 Map<String,ArrayList<Event>> map = new LinkedHashMap<String,ArrayList<Event>>();
		for(String date: getScheduleDates()){
			ArrayList<Event> eventsInDate = new ArrayList<Event>();
			for(Event event : getSchedule()){
				if(event.getDate().equals(date)){
					eventsInDate.add(event);
				}
				
			}
			map.put(date, eventsInDate);
		}
		
		return map;
	}
	
	// Gets an array of arrays of events
	// The inner array list contains a list of events per day
	public ArrayList<ArrayList<Event>> getArrayListEventsPerDate(){
		ArrayList<ArrayList<Event>> list = new ArrayList<ArrayList<Event>>();
		for(String date: getScheduleDates()){
			ArrayList<Event> eventsInDate = new ArrayList<Event>();
			for(Event event : getSchedule()){
				if(event.getDate().equals(date)){
					eventsInDate.add(event);
				}
				
			}
			list.add(eventsInDate);
		}
		
		return list;
	}
	
}
