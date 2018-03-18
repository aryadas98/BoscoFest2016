package com.boscofest.fest16;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventsListAdapter extends BaseAdapter{   
    List <String> titles;
    List <String> subtitles;
    //int[] event_icons;
    Context context;
    int w;
    private static LayoutInflater inflater=null;
    
    public EventsListAdapter (MainActivity mainActivity, List<String> titles, List<String> subtitles) {
        // TODO Auto-generated constructor stub
    	this.titles = titles;
    	this.subtitles = subtitles;
    	//this.event_icons = event_icons;
        context=mainActivity;
        float scale = context.getResources().getDisplayMetrics().density;
        w = (int) ((float) 55 * scale);
         inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        ImageView icon;
        TextView title;
        TextView subtitle;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
    	Holder holder;
    	if (convertView == null) {
	        holder=new Holder();      
	        convertView = inflater.inflate(R.layout.events_list_item, parent, false);
	        
	        holder.title=(TextView) convertView.findViewById(R.id.event_title);
	        holder.subtitle=(TextView) convertView.findViewById(R.id.event_subtitle);
	        holder.icon=(ImageView) convertView.findViewById(R.id.event_icon);
	        convertView.setTag(holder);
	        
    	} else 
    		holder = (Holder)convertView.getTag();
    	
    	Picasso.with(context).load("file:///android_asset/eventpics/"+titles.get(position)+".jpg").placeholder(R.drawable.loadimgcirc).resize(w,w).centerCrop().transform(new CropCircleTransformation()).into(holder.icon);
    	holder.title.setText(titles.get(position));
    	holder.subtitle.setText(subtitles.get(position));
        return convertView;
    }
}
