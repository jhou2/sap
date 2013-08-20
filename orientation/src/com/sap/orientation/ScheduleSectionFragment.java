package com.sap.orientation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sap.orientation.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class ScheduleSectionFragment extends Fragment{
	   
	Schedule schedule;
	   ArrayList<Event> events;
	   private ExpandableListAdapter expListAdapter;
	   
	   public static final String ARG_SECTION_NUMBER = "section_number";
	
	   public ScheduleSectionFragment(){
	
	   }
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    

     // Grab schedule data
        
        AssetManager assetMgr = getActivity().getAssets();
    	InputStream file;
		
    	try {
			file = assetMgr.open("schedule.txt");
			schedule = new Schedule();
			schedule.populateSchedule(file); // Returns an arraylist of Events
			
			events = schedule.getSchedule();
			
			Event firstEvent = events.get(25);
      
			System.out.println(firstEvent.date);
			System.out.println(firstEvent.time);
			System.out.println(firstEvent.title);
			System.out.println(firstEvent.room);
			System.out.println(firstEvent.presenters);
			System.out.println(firstEvent.descr);
			
			file.close();
    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	expListAdapter = new ScheduleListAdapter(schedule);
    	    	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        ExpandableListView expandListView = (ExpandableListView) rootView.findViewById(R.id.schedule_expandable);
        expandListView.setAdapter(expListAdapter);        
        return rootView;
    }
	
	
	// An expandable list adapter that has the dates as the group views, and Events as the children
	// Feed in a schedule, that will con
	public class ScheduleListAdapter extends BaseExpandableListAdapter{
		private ArrayList<String> dates;
		
		//private Map<String, ArrayList<Event>> mapEvents;

		private ArrayList<ArrayList<Event>> eventsPerDate;
		
		public ScheduleListAdapter(Schedule schedule){
			dates = schedule.getScheduleDates();
			//mapEvents = schedule.getMapDateEvents();
			eventsPerDate = schedule.getArrayListEventsPerDate();
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return eventsPerDate.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return eventsPerDate.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return dates.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return dates.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView textView = new TextView(ScheduleSectionFragment.this.getActivity());
			textView.setText(getGroup(groupPosition).toString());
			return textView;
			
			
		}
		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView textView = new TextView(ScheduleSectionFragment.this.getActivity());
			Event childEvent = (Event) getChild(groupPosition,childPosition);
			textView.setText(childEvent.getTime() + "    " + childEvent.getTitle());
			
			
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
	
	
	public class ScheduleSubListAdapter extends BaseExpandableListAdapter{
		private ArrayList<String> dates;
		
		//private Map<String, ArrayList<Event>> mapEvents;

		private ArrayList<ArrayList<Event>> eventsPerDate;
		
		public ScheduleSubListAdapter(Schedule schedule){
			dates = schedule.getScheduleDates();
			//mapEvents = schedule.getMapDateEvents();
			eventsPerDate = schedule.getArrayListEventsPerDate();
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return eventsPerDate.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return eventsPerDate.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return dates.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return dates.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView textView = new TextView(ScheduleSectionFragment.this.getActivity());
			textView.setText(getGroup(groupPosition).toString());
			return textView;
			
			
		}
		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView textView = new TextView(ScheduleSectionFragment.this.getActivity());
//			Event childEvent = (Event) getChild(groupPosition,childPosition);
//			textView.setText(childEvent.getTime() + "    " + childEvent.getTitle());
			
			
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
}
