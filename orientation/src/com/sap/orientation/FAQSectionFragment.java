package com.sap.orientation;

import com.sap.orientation.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FAQSectionFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";

	public FAQSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_faq, container,
				false);
		TextView dummyTextView = (TextView)
		rootView.findViewById(R.id.list_faq);
		dummyTextView.setText("When do I get paid?\n\n" +
		"You are paid on the 15th and last day of each month.\n\n" +
				"What's my I-number?\n\n");

		return rootView;
	}
	
}
