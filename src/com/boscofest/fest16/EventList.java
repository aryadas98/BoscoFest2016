package com.boscofest.fest16;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class EventList extends Fragment {
	List<Event> events;
	List<String> titles;
	List<String> subtitles;
	Bundle bundle;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getArguments();
		events = (List<Event>) bundle.getSerializable("eventsList");

	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {	
		return inflater.inflate(R.layout.activity_events_list, parent, false);
    }
	
	@Override
	public void onViewCreated(View view, Bundle savedInstance) {
		titles = new ArrayList <String> ();
		subtitles = new ArrayList <String> ();
		getActivity().setTitle("Events");
		
		for (int i=0; i<events.size(); i++) {
			titles.add(events.get(i).name);
			subtitles.add(events.get(i).description);
		}
		System.out.println(events.size());
		EventsListAdapter adapter = new EventsListAdapter((MainActivity)getActivity(), titles, subtitles);
		
		ListView list = (ListView) view.findViewById(R.id.events_listview);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new EventClickListener());
	}
	
	private class EventClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	Fragment specificEvent = new EventTab(events.get(position).rules, bundle);
	        getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, specificEvent)
            .addToBackStack(null)
            .commit();
	        
	        getActivity().setTitle(titles.get(position));
	    }
	}

}