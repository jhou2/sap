package com.sap.orientation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FAQSectionFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	
	QuestionManager questionMgr;
	ArrayList<Question> FAQ;
	private ExpandableListAdapter expListAdapter;
	
	public static final String ARG_SECTION_NUMBER = "section_number";

	public FAQSectionFragment() {
	}

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    

     // Grab Question data
        
        AssetManager assetMgr = getActivity().getAssets();
    	InputStream file;
		
    	try {
			file = assetMgr.open("faq.txt");
			questionMgr = new QuestionManager();
			questionMgr.populateQuestion(file); // Returns an arraylist of Question
			
			FAQ = questionMgr.getFAQ();
			
			for (Question p : FAQ){
				System.out.println(p.question);
				System.out.println(p.answer);
			}
			
			file.close();
    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	expListAdapter = new QuestionListAdapter(questionMgr);
    	    	
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);
        ExpandableListView expandListView = (ExpandableListView) rootView.findViewById(R.id.faq_expandable);
        
        int width = expandListView.getWidth();
		expandListView.setIndicatorBounds(650,700);
		
        expandListView.setAdapter(expListAdapter);        
        
        return rootView;
    }

	
	// An expandable list adapter that has the questions as the group views, and answers as the children
		// Feed in a Question Manager, that will con
		public class QuestionListAdapter extends BaseExpandableListAdapter{
			private ArrayList<String> questions;
			
			private ArrayList<String> answers;
			//private ArrayList<ArrayList<Question>> eventsPerDate;
			
			public QuestionListAdapter(QuestionManager questionManager){
				questions = questionManager.getQuestions();
				answers = questionManager.getAnswers();
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return answers.get(groupPosition);
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return 1;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return questions.get(groupPosition);
			}

			@Override
			public int getGroupCount() {
				return questions.size();
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				TextView textView = new TextView(FAQSectionFragment.this.getActivity());
				textView.setText(getGroup(groupPosition).toString());
				textView.setPadding(5, 16, 5, 16);
				textView.setBackgroundColor(FAQSectionFragment.this.getActivity().getResources().getColor(R.color.SAP_GOLD));
				textView.setTextColor(Color.WHITE);
				return textView;
				
				
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView textView = new TextView(FAQSectionFragment.this.getActivity());
				String childEvent = (String) getChild(groupPosition,childPosition);
				textView.setText(childEvent);
				textView.setPadding(36, 10, 0, 10);
				
				return textView;
			}
			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				return true;
			}
		}
		
	
	
} // end FAQ