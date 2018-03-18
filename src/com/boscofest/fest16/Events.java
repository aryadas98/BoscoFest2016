package com.boscofest.fest16;

import java.io.Serializable;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

public class Events extends Fragment {
	List<Event> onstage, offstage;
	ImageView download;
	Bundle bundle;
	
	Events (List<Event> onstage, List<Event> offstage, Bundle bundle) {
		this.onstage = onstage;
		this.offstage = offstage;
		this.bundle = bundle;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_events,container, false);
		download = ((MainActivity)getActivity()).download;
		download.setVisibility(View.VISIBLE);
		
		FragmentTabHost mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		
		Bundle on = new Bundle(bundle); on.putSerializable("eventsList", (Serializable) onstage);
		Bundle off = new Bundle(bundle); off.putSerializable("eventsList", (Serializable) offstage);

		mTabHost.addTab(mTabHost.newTabSpec("onStage").setIndicator("On Stage"), EventList.class, on);
		mTabHost.addTab(mTabHost.newTabSpec("offStage").setIndicator("Off Stage"), EventList.class, off);
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
}