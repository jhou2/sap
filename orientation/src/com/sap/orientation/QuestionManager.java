package com.sap.orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuestionManager {

ArrayList<Question> allQuestions;
	
	
	public QuestionManager(){
		allQuestions = new ArrayList<Question>();
	}
	
	public void populateQuestion(InputStream fileToRead){
	
		// Reads file
		BufferedReader input = new BufferedReader(new InputStreamReader(fileToRead));    	
		
		Question question;
		String line;
	
		try {
			line = input.readLine();
			while (line != null){
				question = new Question();
				
				question.question = line;
				question.answer = input.readLine();

				allQuestions.add(question);
				line = input.readLine(); // Reads the next line (question, if there is another question to read in the file)
			} // End while
			
		input.close();	//close BufferedReader
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	}
	
	public ArrayList<Question> getFAQ(){
		return allQuestions;
	}
	
	
	// Returns a list of the questions
	public ArrayList<String> getQuestions(){
			
		ArrayList<String> questions = new ArrayList<String>();
				
			
		for (Question question : allQuestions){
				
			String q = question.getQuestion();
				
			if(!questions.contains(q)){
					questions.add(q);
				
			}
			
		}
			
		return questions;
	}
	
	// Returns a list of the answers
		public ArrayList<String> getAnswers(){
				
			ArrayList<String> answers = new ArrayList<String>();
				
			for (Question question : allQuestions){
					
				String answer = question.getAnswer();
				answers.add(answer);
				
			}
				
			return answers;
		}
	
	
}
