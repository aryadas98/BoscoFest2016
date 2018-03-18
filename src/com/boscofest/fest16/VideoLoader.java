package com.boscofest.fest16;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class VideoLoader extends BaseAdapter {
    private Context context;
    private List<String> videos = new ArrayList<String>();
 
    public VideoLoader ( Context context, List<String> images) {
        this.context = context;
        this.videos = images;
    }
 
    @Override
    public int getCount() {
        return videos.size();
    }
 
    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setMaxHeight(150);
        imageView.setAdjustViewBounds(true);
        imageView.setTag(videos.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent bigvid = new Intent(context, VideoGallery.class);
            	bigvid.putExtra("video", (String)v.getTag());
            	context.startActivity(bigvid);
            }});
        
        String url = "http://boscofest2016.com/app/video_thumbs/"+videos.get(position)+".jpg";
        
        try{
        Picasso.with(context).load(url).placeholder(R.drawable.loadimg).into(imageView);
        } catch (Exception e) {e.printStackTrace();}
        return imageView;
    }
}