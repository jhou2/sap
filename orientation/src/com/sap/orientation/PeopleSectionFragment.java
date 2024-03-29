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

public class PeopleSectionFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	
	PeopleManager pplMger;
	ArrayList<People> people;
	private ExpandableListAdapter expListAdapter;
	
	public static final String ARG_SECTION_NUMBER = "section_number";

	public PeopleSectionFragment() {
	}

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    

     // Grab people data
        
        AssetManager assetMgr = getActivity().getAssets();
    	InputStream file;
		
    	try {
			file = assetMgr.open("people.txt");
			pplMger = new PeopleManager();
			pplMger.populatePeople(file); // Returns an arraylist of People
			
			people = pplMger.getPeople();
			
//			for (People p : people){
//				System.out.println(p.name);
//				System.out.println(p.bio);
//			}
			
			file.close();
    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	expListAdapter = new PeopleListAdapter(pplMger);
    	    	
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_people, container, false);
        ExpandableListView expandListView = (ExpandableListView) rootView.findViewById(R.id.people_expandable);
        
        int width = expandListView.getWidth();

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR1){
			expandListView.setIndicatorBounds(width-50,width);
		} else {
			expandListView.setGroupIndicator(null);;
		} 

        if(android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1){
			expandListView.setIndicatorBounds(650,700);
		} else {
			//expandListView.setIndicatorBoundsRelative(650,700);
			expandListView.setGroupIndicator(null);
		} 		

        expandListView.setAdapter(expListAdapter);        
        
        return rootView;
    }

	
	// An expandable list adapter that has the names as the group views, and bios as the children
		// Feed in a People Manager, that will con
		public class PeopleListAdapter extends BaseExpandableListAdapter{
			private ArrayList<String> names;
			
			private ArrayList<String> bios;
			//private ArrayList<ArrayList<People>> eventsPerDate;
			
			public PeopleListAdapter(PeopleManager pplManager){
				names = pplManager.getPeopleNames();
				bios = pplManager.getPeopleBios();
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return bios.get(groupPosition);
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
				return names.get(groupPosition);
			}

			@Override
			public int getGroupCount() {
				return names.size();
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				TextView textView = new TextView(PeopleSectionFragment.this.getActivity());
				textView.setText(getGroup(groupPosition).toString());
				textView.setPadding(5, 16, 5, 16);
				textView.setBackgroundColor(PeopleSectionFragment.this.getActivity().getResources().getColor(R.color.SAP_GOLD));
				textView.setTextColor(Color.WHITE);
				return textView;
				
				
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView textView = new TextView(PeopleSectionFragment.this.getActivity());
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
		
	
	
} // end PeopleSectionFragment