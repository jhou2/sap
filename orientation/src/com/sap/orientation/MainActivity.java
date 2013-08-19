package com.sap.orientation;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Locale;

import android.content.res.AssetManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	private static ImageView image1;

	// Set up the action bar.

	// Specify that the Home/Up button should not be enabled, since there is no
	// hierarchical
	// parent.
	// actionBar.setHomeButtonEnabled();

	// Specify that we will be displaying tabs in the action bar.
	// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// Grab schedule data

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Bundle args = new Bundle();
			Fragment fragment;

			switch (position) {
			case 0:
				fragment = new ScheduleSectionFragment();
				args.putInt(ScheduleSectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			case 1:
				fragment = new MapSectionFragment();
				args.putInt(MapSectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;

			case 2:
				fragment = new PeopleSectionFragment();
				args.putInt(PeopleSectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;

			case 3:
				fragment = new FAQSectionFragment();
				args.putInt(FAQSectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			}

			fragment = new DummySectionFragment();
			args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_schedule_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			// dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			dummyTextView.setText("hello");
			dummyTextView.setText("world");
			return rootView;
		}
	} // end DummySectionFragment

	public static class ScheduleSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public ScheduleSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_schedule_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView
					.setText("Tuesday Sept 3, 2013 \n8:30-9:00AM \n"
							+ "Welcome & Ice Breaker Session\nGalileo Room\n"
							+ "Rajdeep Randhawa, Jaismin Parmar, Carrie Cheung, Chelsea Lee\n"
							+ "Welcome to SAP! Get to know your fellow interns with an " +
							"interactive ice breaker to jumpstart the day.\n"
							+ "\n" + "9:00-11:00AM\n"
							+ "New Employee Welcome Session\n" + "Galileo Room\n"
							+ "Agnes Garaba" + "\n");

			return rootView;
		}

	} // end ScheduleSectionFragment

	public static class PeopleSectionFragment extends Fragment {
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

	public static class MapSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public MapSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_map, container,
					false);
			image1 = (ImageView) rootView.findViewById(R.id.imageView1);
			image1.setImageResource(R.drawable.map);

			return rootView;
		}

	} // end MapSectionFragment

	public static class FAQSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
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

	} // end FAQSectionFragment

}
