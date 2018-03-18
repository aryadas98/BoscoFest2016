package com.boscofest.fest16;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Schools extends Fragment {
	List<School> schools;
	
	Schools (List<School> schools) {
		this.schools = schools;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// We are using the layout.
		return inflater.inflate(R.layout.activity_schools, parent, false);
    }
	
	@Override
	public void onViewCreated(View view, Bundle savedInstance) {
		List<String> names = new ArrayList<String> ();
		List<String> mottos = new ArrayList<String> ();
		
		for (int i=0; i<schools.size(); i++) {
			names.add(schools.get(i).name);
			mottos.add(schools.get(i).motto);
		}
		
		//int[] icons = new int[] {R.drawable.ic_dbpc, R.drawable.ic_dbl, R.drawable.ic_dbb, R.drawable.ic_loreto}; 
		
		SchoolsAdapter adapter = new SchoolsAdapter ((MainActivity)getActivity(), names, mottos);
		ListView list = (ListView) view.findViewById(R.id.schools_listview);
		list.setAdapter(adapter);
	}
}