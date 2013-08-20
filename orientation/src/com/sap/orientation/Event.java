package com.sap.orientation;

public class Event {

	String date;
	String time;
	String title;
	String room;
	String presenters;
	String descr;
	
	public Event(){
		
	}
	
	public Event(String pDate, String pTime, String pTitle, String pRoom, String pPresenters, String pDescr){
		
		date = pDate;
		time = pTime;
		title = pTitle;
		room = pRoom;
		presenters = pPresenters;
		descr = pDescr;
	}
	
	public String getDate(){return date;}
	public String getTime(){return time;}
	public String getTitle(){return title;}
	public String getRoom(){return room;}
	public String getPresenters(){return presenters;}
	public String getDescription(){return descr;}
	
}
