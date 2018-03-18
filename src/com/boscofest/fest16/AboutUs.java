package com.boscofest.fest16;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends Fragment {
	TextView rateapp;
	ImageView fb;
	ImageView web;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_about_us, parent, false);
		rateapp = (TextView) v.findViewById(R.id.rateapp);
		fb = (ImageView) v.findViewById(R.id.about_facebook);
		web = (ImageView) v.findViewById(R.id.about_web);
		
		String rate = "App Version: 2.2<br/>Rate this app on <a href=\"market://details?id=com.boscofest.fest16\">Google Play!</a>";
		rateapp.setText(Html.fromHtml(rate));
		rateapp.setMovementMethod(LinkMovementMethod.getInstance());
		
		fb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/boscofest2016/")));
			    }
		});
		
		web.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://boscofest2016.com")));
			    }
		});
		
		return v;
    }
}