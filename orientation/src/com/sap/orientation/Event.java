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
	
}
