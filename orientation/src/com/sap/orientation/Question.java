package com.sap.orientation;

public class Question {

	String question;
	String answer;
	
	
	public Question(){
		
	}
	
	public Question(String pQuestion, String pAnswer){
		
		question = pQuestion;
		answer = pAnswer;
	}
	
	public String getQuestion(){return question;}
	public String getAnswer(){return answer;}
	
	
}
