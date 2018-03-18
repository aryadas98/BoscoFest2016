package com.boscofest.fest16;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Rules extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_rules, parent, false);
		TextView txt = (TextView) v.findViewById(R.id.rules);
		ImageView poster = (ImageView) v.findViewById(R.id.poster);
		String title = getArguments().getString("title");
		String rules = getArguments().getString("rules");
		
		txt.setText(rules);
		Picasso.with(getContext()).load("file:///android_asset/eventpics/"+title+".jpg").placeholder(R.drawable.loadimg).into(poster);
		return v;
    }
}