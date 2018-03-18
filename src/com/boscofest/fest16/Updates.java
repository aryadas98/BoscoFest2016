package com.boscofest.fest16;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

public class Updates extends Fragment {
	ListView postList;
	List<Post> posts;
	List<Post> subPosts;
	List<String> event_names;
	Spinner filter;
	int event;
	boolean toggle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		posts = (List<Post>)(getArguments().getSerializable("post_list"));
		event_names = getArguments().getStringArrayList("events_list");
		event = getArguments().getInt("event");
		toggle = getArguments().getBoolean("toggle");
		filter = ((MainActivity)getActivity()).filter;
		subPosts = new ArrayList<Post>();
		subPosts = filter(event);
		System.out.println("I've got "+event_names.size()+" events");
		System.out.println("I've got "+posts.size()+" posts.");
	}
	
	List<Post> filter (int event) {
		if (event == 0) return posts;
		
		List<Post> temp = new ArrayList<Post>();
		for (int i=0; i<posts.size(); i++)
			if (posts.get(i).event == event)
				temp.add(posts.get(i));
		return temp;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_updates, parent, false);
	        
        postList = (ListView) view.findViewById(R.id.posts_list);
        
        PostAdapter listAdapter = new PostAdapter((MainActivity)getActivity(), subPosts, event_names);
        postList.setAdapter(listAdapter);
        postList.setOnItemClickListener(new ListView.OnItemClickListener() {
        	@Override
    	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		System.out.println("Post clicked: "+position);
        		Fragment fragment = new Update(subPosts.get(position));
        		filter.setVisibility(View.GONE);
        		getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
        	}
        });
        
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        		if((pos != event) & toggle) {
	        		Fragment fragment = new Updates();
	    			Bundle bundle = new Bundle();
	    			bundle.putSerializable("post_list", (Serializable)posts);
	    			bundle.putStringArrayList("events_list", new ArrayList<String>(event_names));
	    			bundle.putInt("event", pos);
	    			bundle.putBoolean("toggle", true);
	    			fragment.setArguments(bundle);
	    			
	        		getActivity().getSupportFragmentManager().beginTransaction()
	                .replace(R.id.content_frame, fragment)
	                .commit();
        		}
        	  }
        	@Override
      	  public void onNothingSelected(AdapterView<?> arg0) {
      		// TODO Auto-generated method stub
      	  }
        });
        
        if (toggle) {
        getActivity().setTitle("Updates");
        ((MainActivity) getActivity()).filter.setVisibility(View.VISIBLE);
        }
        
        return view;
    }
}