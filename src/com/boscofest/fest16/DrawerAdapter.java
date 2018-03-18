package com.boscofest.fest16;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter{   
    String [] actionList;
    int [] iconId;
    int selected;
    private static LayoutInflater inflater=null;
    
    public DrawerAdapter(MainActivity mainActivity, String[] actions, int[] actionicons, int selected) {
        // TODO Auto-generated constructor stub
        actionList=actions;
        //context=mainActivity;
        iconId=actionicons;
        this.selected = selected;
         inflater = ( LayoutInflater )mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public void setSelection(int select) {
    	selected = select;
    	notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return actionList.length;
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
        TextView label;
        ImageView icon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        
        if (convertView == null) {
        	convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
        	
        	holder = new Holder();
        	holder.label=(TextView) convertView.findViewById(R.id.actionlabel);
            holder.icon=(ImageView) convertView.findViewById(R.id.actionicon);
            convertView.setTag(holder);
        }
             
        else holder = (Holder) convertView.getTag();
        
               
         holder.label.setText(actionList[position]);
         holder.icon.setImageResource(iconId[position]);
         
         if (position == selected) convertView.setBackgroundColor(Color.parseColor("#ffcce9"));
         else convertView.setBackgroundColor(Color.WHITE);
         
        return convertView;
    }

} 
