package com.boscofest.fest16;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.TextView;

public class EventTab extends Fragment {
	String rules;
	Bundle bundle;
	
	EventTab (String rules, Bundle bundle) {
		this.rules = rules;
		this.bundle = bundle;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_events,container, false);
		
		FragmentTabHost mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		
		Bundle rulesBundle = new Bundle();
		List<String> event_names = bundle.getStringArrayList("events_list");
		String title = (String)(getActivity().getTitle());
		int event_id = event_names.indexOf(title);
		bundle.putInt("event", event_id);
		bundle.putBoolean("toggle", false);
		
		rulesBundle.putString("rules", rules);
		rulesBundle.putString("title", title);
		mTabHost.addTab(mTabHost.newTabSpec("Info").setIndicator("Info"), Rules.class, rulesBundle);
		mTabHost.addTab(mTabHost.newTabSpec("Updates").setIndicator("Updates"), Updates.class, bundle);
		mTabHost.setCurrentTab(0);
		
		TabWidget widget = mTabHost.getTabWidget();
		for(int i = 0; i < widget.getChildCount(); i++) {
		    View v = widget.getChildAt(i);

		    // Look for the title view to ensure this is an indicator and not a divider.
		    TextView tv = (TextView)v.findViewById(android.R.id.title);
		    if(tv == null) {
		        continue;
		    }
		    v.setBackgroundResource(R.drawable.tabcolor);
		}
		return view;
	}
	
	public void onBackPressed() {
		getActivity().getSupportFragmentManager().popBackStack();
	}
}