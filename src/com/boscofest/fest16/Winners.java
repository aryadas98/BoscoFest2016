package com.boscofest.fest16;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Winners extends Fragment {
	String[] winnersList = {"Bazinga","Delhi Public School","Don Bosco School Park Circus","Loreto House","Break It Bad","Loreto House","Don Bosco School Park Circus","Don Bosco School Bandel","Central Perk","Don Bosco School Liluah","St. Xaviers Collegiate School","Mahadevi Birla World Academy","Daredevil","Don Bosco School Park Circus","Don Bosco School Bandel","Auxilium Convent School Barasat","Flash","Auxilium Convent School Barasat","Don Bosco School Park Circus<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Modern High School","Loreto House","Glee","Don Bosco School Park Circus","Loreto House","Our Lady Queen Of The Missions<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Park Circus","Heisenberg","St. Xaviers Collegiate School","Don Bosco School Park Circus","Delhi Public School","Impact","Our Lady Queen Of The Missions<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Salt Lake","Don Bosco School Park Circus","Calcutta Girls High School","Knight's Watch","The Heritage School","St. James School","Delhi Public School","Mad Men","Don Bosco School Bandel","Don Bosco School Liluah","St. Xaviers Collegiate School","Looney","St. Xaviers Collegiate School","Don Bosco School Park Circus","Pratt Memorial School","Nritya","Auxilium Convent School Barasat","Don Bosco School Liluah","Calcutta Girls High School","Pretty Little Liars","Don Bosco School Park Circus","St. James School","Mahadevi Birla World Academy","Quantico","La Martiniere For Boys","St. Xaviers Collegiate School","Modern High School","Raag","Our Lady Queen Of The Missions<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Park Circus","Modern High School","Loreto House","Rally (Boys)","Don Bosco School Park Circus<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Team B)","Don Bosco School Park Circus<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Team A)","La Martiniere For Boys","Rally (Girls)","Mahadevi Birla World Academy","Calcutta Girls High School","Modern High School","Tango","Don Bosco School Park Circus","Sushila Birla Girls School","Our Lady Queen Of The Missions<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Salt Lake","Two And Halves","Don Bosco School Park Circus","Sushila Birla Girls School","Mahadevi Birla World Academy"};
		
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		String formattedOutput = "";
		
		for (int i=0; i<winnersList.length; i++) {
			if (i%4 == 0)
				formattedOutput+= "<b>"+winnersList[i]+":</b><br>";
			else if (i%4 == 1)
				formattedOutput+= "&nbsp;&nbsp;&nbsp;&nbsp;1st: "+winnersList[i]+"<br>";
			else if (i%4 == 2)
				formattedOutput+= "&nbsp;&nbsp;&nbsp;&nbsp;2nd: "+winnersList[i]+"<br>";
			else if (i%4 == 3)
				formattedOutput+= "&nbsp;&nbsp;&nbsp;&nbsp;3rd: "+winnersList[i]+"<br><br>";
		}
		
		View v = inflater.inflate(R.layout.activity_winners, parent, false);
		((TextView)v.findViewById(R.id.winnersText)).setText(Html.fromHtml(formattedOutput));
		
		return v;
    }
}