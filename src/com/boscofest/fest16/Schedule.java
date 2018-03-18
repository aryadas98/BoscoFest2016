package com.boscofest.fest16;

import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Schedule extends Fragment {
	List<SchEvent> schedule;
	
	Schedule (List<SchEvent> schedule) {
		this.schedule = schedule;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_schedule, parent, false);
        
        TableLayout table = (TableLayout) v.findViewById(R.id.schedule_table);
        
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
        params.rightMargin = dpToPixel(5); // right-margin = 10dp
        params.bottomMargin = dpToPixel(10);
        
        SchEvent header = new SchEvent();
        header.setId(-1); header.setEvent("SHOW"); header.setVenue("CHANNEL"); header.setTime("TIMING");
        schedule.add(0, header);
        
        for (int i=0; i<schedule.size(); i++) {
            TableRow tr = new TableRow(getActivity());

            TextView event = new TextView(getActivity());
            event.setText(schedule.get(i).event);
            event.setLayoutParams(params);
            event.setTextColor(Color.BLACK);
            event.setGravity(Gravity.CENTER_HORIZONTAL);
            if (i == 0) { event.setTypeface(null, Typeface.BOLD); event.setTextSize(15); }
            tr.addView(event);

            TextView venue = new TextView(getActivity());
            venue.setText(schedule.get(i).venue);
            venue.setLayoutParams(params);
            venue.setTextColor(Color.BLACK);
            venue.setGravity(Gravity.CENTER_HORIZONTAL);
            if (i == 0) { venue.setTypeface(null, Typeface.BOLD); venue.setTextSize(15); }
            tr.addView(venue);

            TextView time = new TextView(getActivity());
            time.setText(schedule.get(i).time);
            time.setLayoutParams(params);
            time.setTextColor(Color.BLACK);
            time.setGravity(Gravity.CENTER_HORIZONTAL);
            if (i == 0) { time.setTypeface(null, Typeface.BOLD); time.setTextSize(15); }
            tr.addView(time);

            table.addView(tr);
            
            if (i == 0) {
            	TableRow tr2 = new TableRow(getActivity());
            	View line = new View (getActivity());
            	TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, dpToPixel(2), 1f);
            	params2.bottomMargin=dpToPixel(10);
            	line.setLayoutParams(params2);
            	line.setBackgroundColor(Color.BLACK);
            	tr2.addView(line);
            	table.addView (tr2);
            }
        }
        
        return v;
    }
    
    int dpToPixel(int dp) {
         float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }
}