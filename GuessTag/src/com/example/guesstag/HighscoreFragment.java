package com.example.guesstag;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HighscoreFragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";

	private ArrayList<String> listOfGames = new ArrayList<String>();
	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static HighscoreFragment newInstance(int sectionNumber) {
		HighscoreFragment fragment = new HighscoreFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public HighscoreFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_highscore,
				container, false);
		
		listOfGames.add("First Game");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, listOfGames);

		final ListView listView = (ListView) rootView.findViewById(R.id.list_item1);
		listView.setAdapter(adapter);
		
		return rootView;
	}
}

