package com.sap.orientation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PeopleSectionFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	public PeopleSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_people,
				container, false);
		TextView dummyTextView = (TextView)
		rootView.findViewById(R.id.list_people);
		dummyTextView.setText("Kirsten Sutton\n" +
		"Managing Director, SAP Labs Canada\n\n" +
				"Cho Wang\n" +
		"Solution Management Intern\n\n" +
				"Agnes Garaba\n" +
		"HR Business Partner\n"
		);

		return rootView;
	}

} // end PeopleSectionFragment