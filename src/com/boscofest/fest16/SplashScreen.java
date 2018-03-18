package com.boscofest.fest16;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class SplashScreen extends Activity {
	
	OkHttpClient client;
	Intent intent ;
	Bundle bundle = new Bundle();
	
	List<Post> posts; 
    List<Event> events;
    List<Event> onstage_events;
    List<Event> offstage_events;
    List<String> all_event_names;
    List<School> schools;
    List<SchEvent> schEvents;
    boolean goAhead = false;
    Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);
		
		ImageView ring = (ImageView) findViewById(R.id.logosring);
		Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.drawable.splashring);
        ring.startAnimation(rotate);
        
		new ReadPosts().execute();
		handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
			  if (goAhead) {
					intent = new Intent(SplashScreen.this, MainActivity.class);
		        	intent.putExtras(bundle);
		            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		            startActivity(intent);
				}
			  else goAhead = true;
		  }
		}, 3000);
	}
	
	
private class ReadPosts extends AsyncTask<String, String, String> {
		
		@Override
		public String doInBackground(String... params){
			
			String url ="http://boscofest2016.com/app/fetch.php";
			String content="";
			
			// Read posts file.
			try {
			BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("posts")));
			String line;
			while((line = br.readLine())!=null)
				content+=line;
			br.close();
			posts = new ObjectMapper().readValue(content,
    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
    				     Post.class));
    		  
			System.out.println("Posts file read. "+content);
    		  
    		} catch (Exception e) {e.printStackTrace(); posts = new ArrayList<Post>();}		
		
			
			content = "";
		      try {
					BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("events")));
					String line;
					while((line = br.readLine())!=null)
						content+=line;
					br.close();
					System.out.println("Events file:"+content);
					events = new ObjectMapper().readValue(content,
		    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
		    				     Event.class));
		    		  
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    			content = "";
		    			try {
		    			BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.event_response)));
						String line;
						while((line = br.readLine())!=null)
							content+=line;
						br.close();
						System.out.println("Events raw:"+content);
						events = new ObjectMapper().readValue(content,
			    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
			    				     Event.class));
		    			} catch (Exception e1) {e1.printStackTrace();}
						}
		      
		      onstage_events = new ArrayList<Event> ();
				offstage_events = new ArrayList<Event> ();
				all_event_names = new ArrayList<String> ();
				
				for (int i=0; i<events.size(); i++) {
					if (events.get(i).getIsOnstage() == 1) onstage_events.add(events.get(i));
					else if (events.get(i).getIsOnstage() == 0) offstage_events.add(events.get(i));
					
					all_event_names.add(events.get(i).getName());
				}
				
				System.out.println(all_event_names.size());
				
				
				content = "";
			      try {
						BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("schools")));
						String line;
						while((line = br.readLine())!=null)
							content+=line;
						br.close();
						System.out.println("Schools file:"+content);
						schools = new ObjectMapper().readValue(content,
			    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
			    				     School.class));
			    		  
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    			content = "";
			    			try {
			    			BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.schools_response)));
							String line;
							while((line = br.readLine())!=null)
								content+=line;
							br.close();
							System.out.println("Schools raw:"+content);
							schools = new ObjectMapper().readValue(content,
				    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
				    				     School.class));
			    			} catch (Exception e1) {e1.printStackTrace();}
							}
			      
					System.out.println(schools.size());
					
					content = "";
				      try {
							BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("schedule")));
							String line;
							while((line = br.readLine())!=null)
								content+=line;
							br.close();
							System.out.println("Schedule file:"+content);
							schEvents = new ObjectMapper().readValue(content,
				    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
				    				     SchEvent.class));
				    		  
				    		} catch (Exception e) {
				    			e.printStackTrace();
				    			content = "";
				    			try {
				    			BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.schedule_response)));
								String line;
								while((line = br.readLine())!=null)
									content+=line;
								br.close();
								System.out.println("Schedule raw:"+content);
								schEvents = new ObjectMapper().readValue(content,
					    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
					    				     SchEvent.class));
				    			} catch (Exception e1) {e1.printStackTrace();}
								}
				      
						System.out.println(schEvents.size());
				
		    // code request code here
			
		      Request request = new Request.Builder()
		          .url(url)
		          .addHeader("Accept-Encoding", "gzip")
		          .build();
		      try{
		      client = new OkHttpClient();
		      Response resp = client.newCall(request).execute();
		      GZIPInputStream input = new GZIPInputStream(resp.body().byteStream());
		      BufferedReader br = new BufferedReader(new InputStreamReader(input));
	            StringBuffer sb = new StringBuffer();
	            String line = "";
	            while((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	          String response = sb.toString();
		      
	    		List<Post> temp = new ObjectMapper().readValue(response,
	    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
	    				     Post.class));
	    		
	    		if (temp.size() > 0 && temp != null)
	    			posts = temp;
	    		
		      } catch (Exception e) {e.printStackTrace();}
		      
		      try {
		      String post_string = new ObjectMapper().writeValueAsString(posts);
		      FileOutputStream outputStream = openFileOutput("posts", Context.MODE_PRIVATE);
	    		outputStream.write(post_string.getBytes());
	    		outputStream.close();
	    		System.out.println("Posts file written");
		      } catch (Exception e) {}
		      
		      /*url ="http://boscofest2016.com/app/fetch_events.php";
		      
				    // code request code here
					
				      request = new Request.Builder()
				          .url(url)
				          .addHeader("Accept-Encoding", "gzip")
				          .build();
				      
				      try{
				      client = new OkHttpClient();
				      Response resp = client.newCall(request).execute();
				      GZIPInputStream input = new GZIPInputStream(resp.body().byteStream());
				      BufferedReader br = new BufferedReader(new InputStreamReader(input));
			            StringBuffer sb = new StringBuffer();
			            String line = "";
			            while((line = br.readLine()) != null) {
			                sb.append(line);
			            }
			          String response = sb.toString();
				      
			    		List<Event> temp = new ObjectMapper().readValue(response,
			    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
			    				     Event.class));
			    		
			    		if (temp.size() > 0)
			    			events = temp;
			    		
				      } catch (Exception e) {e.printStackTrace();}
				      
				      try {
				      String event_string = new ObjectMapper().writeValueAsString(events);
				      FileOutputStream outputStream = openFileOutput("events", Context.MODE_PRIVATE);
			    		outputStream.write(event_string.getBytes());
			    		outputStream.close();
			    		System.out.println("Events file written");
				      } catch (Exception e) {}
				
				onstage_events = new ArrayList<Event> ();
				offstage_events = new ArrayList<Event> ();
				all_event_names = new ArrayList<String> ();
				
				for (int i=0; i<events.size(); i++) {
					if (events.get(i).getIsOnstage() == 1) onstage_events.add(events.get(i));
					else if (events.get(i).getIsOnstage() == 0) offstage_events.add(events.get(i));
					
					all_event_names.add(events.get(i).getName());
				}*/
				
				url ="http://boscofest2016.com/app/fetch_schools.php";
			      
			    // code request code here
				
			      request = new Request.Builder()
			          .url(url)
			          .addHeader("Accept-Encoding", "gzip")
			          .build();
			      
			      try{
			      client = new OkHttpClient();
			      Response resp = client.newCall(request).execute();
			      GZIPInputStream input = new GZIPInputStream(resp.body().byteStream());
			      BufferedReader br = new BufferedReader(new InputStreamReader(input));
		            StringBuffer sb = new StringBuffer();
		            String line = "";
		            while((line = br.readLine()) != null) {
		                sb.append(line);
		            }
		          String response = sb.toString();
			      
		    		List<School> temp = new ObjectMapper().readValue(response,
		    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
		    				     School.class));
		    		
		    		if (temp.size() > 0)
		    			schools = temp;
		    		
			      } catch (Exception e) {e.printStackTrace();}
			      
			      try {
			      String event_string = new ObjectMapper().writeValueAsString(schools);
			      FileOutputStream outputStream = openFileOutput("schools", Context.MODE_PRIVATE);
		    		outputStream.write(event_string.getBytes());
		    		outputStream.close();
		    		System.out.println("Events file written");
			      } catch (Exception e) {}
			      
			      url ="http://boscofest2016.com/app/fetch_schedule.php";
			      
				    // code request code here
					
				      request = new Request.Builder()
				          .url(url)
				          .addHeader("Accept-Encoding", "gzip")
				          .build();
				      
				      try{
				      client = new OkHttpClient();
				      Response resp = client.newCall(request).execute();
				      GZIPInputStream input = new GZIPInputStream(resp.body().byteStream());
				      BufferedReader br = new BufferedReader(new InputStreamReader(input));
			            StringBuffer sb = new StringBuffer();
			            String line = "";
			            while((line = br.readLine()) != null) {
			                sb.append(line);
			            }
			          String response = sb.toString();
				      
			    		List<SchEvent> temp = new ObjectMapper().readValue(response,
			    				  TypeFactory.defaultInstance().constructCollectionType(List.class,  
			    				     SchEvent.class));
			    		
			    		if (temp.size() > 0)
			    			schEvents = temp;
			    		
				      } catch (Exception e) {e.printStackTrace();}
				      
				      try {
				      String event_string = new ObjectMapper().writeValueAsString(events);
				      FileOutputStream outputStream = openFileOutput("schedule", Context.MODE_PRIVATE);
			    		outputStream.write(event_string.getBytes());
			    		outputStream.close();
			    		System.out.println("Schedule file written");
				      } catch (Exception e) {}
			      
				System.out.println(all_event_names.size());
				System.out.println(posts.size());
				System.out.println(schools.size());
				
				bundle.putStringArrayList("events_list", new ArrayList<String>(all_event_names));
    			bundle.putSerializable("post_list", (Serializable)posts);
    			bundle.putSerializable("events", (Serializable)events);
    			bundle.putSerializable("onstage", (Serializable)onstage_events);
				bundle.putSerializable("offstage", (Serializable)offstage_events);
				bundle.putSerializable("schools", (Serializable)schools);
				bundle.putSerializable("schedule", (Serializable)schEvents);
   
			return "";
		}
		
		@Override
		protected void onPostExecute(String response) {
			if (goAhead) {
				intent = new Intent(SplashScreen.this, MainActivity.class);
	        	intent.putExtras(bundle);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	            startActivity(intent);
			} else goAhead = true;
		}
	}
}
