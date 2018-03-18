package com.boscofest.fest16;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OurTeam extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// We are using the layout.
		return inflater.inflate(R.layout.activity_our_team, parent, false);
    }
}