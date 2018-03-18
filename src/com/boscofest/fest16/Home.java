package com.boscofest.fest16;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Home extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// We are using the layout.
		
		View view = inflater.inflate(R.layout.activity_home, parent, false);
		ImageView img = (ImageView) view.findViewById(R.id.imageView1);
		Picasso.with(getContext()).load("http://boscofest2016app.netne.net/images/p1.jpg").into(img);
		return view;
    }
}