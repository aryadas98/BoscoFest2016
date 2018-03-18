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

public class ImageLoader extends BaseAdapter {
    private Context context;
    private List<String> images = new ArrayList<String>();
 
    public ImageLoader ( Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }
 
    @Override
    public int getCount() {
        return this.images.size();
    }
 
    @Override
    public Object getItem(int position) {
        return this.images.get(position);
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
        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent bigpic = new Intent(context, ImageGallery.class);
            	bigpic.putStringArrayListExtra("imageList", (ArrayList<String>) images);
            	bigpic.putExtra("pos", (Integer)(v.getTag()));
            	context.startActivity(bigpic);
            }});
        
        String url = "http://boscofest2016.com/app/images/"+images.get(position)+".jpg";
        
        try{
        Picasso.with(context).load(url).placeholder(R.drawable.loadimg).into(imageView);
        } catch (Exception e) {e.printStackTrace();}
        return imageView;
    }
}