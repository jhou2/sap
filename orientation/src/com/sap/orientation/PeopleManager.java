package com.sap.orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PeopleManager {

ArrayList<People> allPeople;
	
	
	public PeopleManager(){
		allPeople = new ArrayList<People>();
	}
	
	public void populatePeople(InputStream fileToRead){
	
		// Reads file
		BufferedReader input = new BufferedReader(new InputStreamReader(fileToRead));    	
		
		People person;
		String line;
	
		try {
			line = input.readLine();
			while (line != null){
				person = new People();
				
				person.name = line;
				person.bio = input.readLine();

				allPeople.add(person);
				line = input.readLine(); // Reads the next line (name, if there is another person to read in the file)
			} // End while
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	}
	
	public ArrayList<People> getPeople(){
		return allPeople;
	}
	
	
	// Returns a list of the presenter names
	public ArrayList<String> getPeopleNames(){
			
		ArrayList<String> names = new ArrayList<String>();
				
			
		for (People person : allPeople){
				
			String name = person.getName();
				
			if(!names.contains(name)){
					names.add(name);
				
			}
			
		}
			
		return names;
	}
	
	// Returns a list of the presenter names
		public ArrayList<String> getPeopleBios(){
				
			ArrayList<String> bios = new ArrayList<String>();
				
			for (People person : allPeople){
					
				String bio = person.getBio();
					
				if(!bios.contains(bio)){
						bios.add(bio);
					
				}
				
			}
				
			return bios;
		}
	
	
}
