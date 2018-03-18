package com.boscofest.fest16;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Updater extends BroadcastReceiver {
	//Context context;
	
    @Override  
    public void onReceive(Context context, Intent intent) {
    	//this.context = context;
        //new RealUpdater(context).execute();
    }
    
    /*private class RealUpdater extends AsyncTask<String,String,String> {
    	
    	@Override
    	public String doInBackground(String... args) {
    		int last = 0;
    		
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("lastpost")));
    			last = Integer.parseInt(br.readLine());
    			System.out.println(last);
    			br.close();
    		} catch (Exception e) {}
    		
    		String url = "http://boscofest2016.com/app/newposts.php?after="+last;
    		
    		Request request = new Request.Builder()
	          .url(url)
	          .addHeader("Accept-Encoding", "gzip")
	          .build();
    		
    		try{
  		      OkHttpClient client = new OkHttpClient();
  		      Response resp = client.newCall(request).execute();
  		      int newlast = Integer.parseInt(resp.body().string());
  		      System.out.println("New last: "+newlast);
    		
    		return "";
    	}
    } */
}  