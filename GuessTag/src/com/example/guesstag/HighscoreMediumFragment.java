package com.example.guesstag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class HighscoreMediumFragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";

	ArrayList<HighscoreListItem> hl;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static HighscoreMediumFragment newInstance(int sectionNumber) {
		HighscoreMediumFragment fragment = new HighscoreMediumFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public HighscoreMediumFragment() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_highscore, container, false);
		hl = HighscoreList.getHighscoreList().getMediumScores();
		TextView textView =(TextView)rootView.findViewById(R.id.text);
		
			Collections.sort(hl, new Comparator(){

            public int compare(Object o1, Object o2) {
            	HighscoreListItem p1 = (HighscoreListItem) o1;
                HighscoreListItem p2 = (HighscoreListItem) o2;
               return (p2.getPoints())-(p1.getPoints());
            }
        });
			
			ListView listView = (ListView) rootView.findViewById(R.id.list_item1);
			View header = (View)inflater.inflate(R.layout.header_layout, null, false);
			listView.addHeaderView(header);
			
			if (hl.isEmpty()==true){
				textView.setVisibility(TextView.VISIBLE);
				
			}else{
				textView.setVisibility(TextView.INVISIBLE);
				ScoreAdapter adapter = new ScoreAdapter(getActivity(), hl);
				listView.setAdapter(adapter);
			}
		return rootView;
	}
	
	public void onClickBack(View view) {
		Intent intent = new Intent(getActivity(), StartActivity.class);
		startActivity(intent);
	}
}

