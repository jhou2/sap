package com.sap.orientation;

import java.util.ArrayList;

public class People {

	String name;
	String bio;
	
	
	public People(){
		
	}
	
	public People(String pName, String pBio){
		
		name = pName;
		bio = pBio;
	}
	
	public String getName(){return name;}
	public String getBio(){return bio;}
	
	
}
