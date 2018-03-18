package com.boscofest.fest16;

import java.util.List;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostAdapter extends BaseAdapter{   
    List<Post> posts;
    List<String> events;
    Context context;
    
    private static LayoutInflater inflater=null;
    
    public PostAdapter (MainActivity mainActivity, List<Post> posts, List<String> events) {
        // TODO Auto-generated constructor stub
    	this.posts = posts;
    	this.events = events;
        context = mainActivity;
         inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return posts.size();
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
        TextView content;
        TextView time;
        TextView viewMore;
        TextView event;
        ImageView preview1;
        ImageView preview2;
        ImageView preview3;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
    	Holder holder;
    	if (convertView == null) {
	        holder=new Holder();      
	        convertView = inflater.inflate(R.layout.post_item, parent, false);
	        
	        holder.content=(TextView) convertView.findViewById(R.id.post_content);
	        holder.time = (TextView) convertView.findViewById(R.id.post_time);
	        holder.event = (TextView) convertView.findViewById(R.id.event);
	        holder.viewMore = (TextView) convertView.findViewById(R.id.viewmore);
	        holder.preview1 = (ImageView) convertView.findViewById(R.id.preview1);
	        holder.preview2 = (ImageView) convertView.findViewById(R.id.preview2);
	        holder.preview3 = (ImageView) convertView.findViewById(R.id.preview3);
	        convertView.setTag(holder);
	        
    	} else 
    		holder = (Holder)convertView.getTag();
    	
		holder.content.setText(posts.get(position).content);
		Linkify.addLinks(holder.content, Linkify.WEB_URLS);
	    holder.time.setText(posts.get(position).timeToString());
	    holder.event.setText(events.get(posts.get(position).event));
	    
	    int no = posts.get(position).images.size();
	    
	    String image_link1, image_link2;
	    
	    switch (no) {
	    	case 0:
	    		holder.preview1.setVisibility(View.GONE);
	    		holder.preview2.setVisibility(View.GONE);
	    		holder.preview3.setVisibility(View.GONE);
	    		break;
	    	
	    	case 1:
	    		holder.preview1.setVisibility(View.VISIBLE);
	    		holder.preview2.setVisibility(View.GONE);
	    		holder.preview3.setVisibility(View.GONE);
		    	
	    		image_link1 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(0)+".jpg";
	    	    
	    		Picasso.with(context).load(image_link1).placeholder(R.drawable.loadimg).into(holder.preview1);
	    		break;
	    	
	    	case 2:
	    		holder.preview1.setVisibility(View.VISIBLE);
	    		holder.preview2.setVisibility(View.VISIBLE);
	    		holder.preview3.setVisibility(View.GONE);
		    	
	    		image_link1 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(0)+".jpg";
	    		image_link2 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(1)+".jpg";
	    	    
	    		Picasso.with(context).load(image_link1).placeholder(R.drawable.loadimg).into(holder.preview1);
	    		Picasso.with(context).load(image_link2).placeholder(R.drawable.loadimg).into(holder.preview2);
	    		break;
	    	
	    	default:
	    		holder.preview1.setVisibility(View.VISIBLE);
	    		holder.preview2.setVisibility(View.VISIBLE);
	    		holder.preview3.setVisibility(View.VISIBLE);
		    	
	    		image_link1 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(0)+".jpg";
	    	    image_link2 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(1)+".jpg";
	    	    String image_link3 = "http://boscofest2016.com/app/images/"+posts.get(position).images.get(2)+".jpg";
	    	    
	    		Picasso.with(context).load(image_link1).placeholder(R.drawable.loadimg).into(holder.preview1);;
	    		Picasso.with(context).load(image_link2).placeholder(R.drawable.loadimg).into(holder.preview2);
	    		Picasso.with(context).load(image_link3).placeholder(R.drawable.loadimg).into(holder.preview3);
	    }
	    	
	    	if (posts.get(position).images.size() > 3) {
	    		holder.viewMore.setVisibility(View.VISIBLE);
	    		holder.viewMore.setText("+"+(posts.get(position).images.size() - 3) +" more...");
	    	}	
	    	else
	    		holder.viewMore.setVisibility(View.GONE);
	    	
        return convertView;
    }
} 
