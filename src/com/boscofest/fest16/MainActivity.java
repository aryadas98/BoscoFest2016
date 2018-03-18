package com.boscofest.fest16;

import java.util.List;

import okhttp3.OkHttpClient;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private String[] options;
    private DrawerLayout mDrawerLayout;
    public Toolbar toolbar;
    private ListView mDrawerList;
    private RelativeLayout mDrawerRLayout;
    private DrawerAdapter listAdapter;
    public OkHttpClient client;
    private CountDownTimer ticktock;
    private TextView countdown_days;
    private TextView countdown_hours;
    private TextView countdown_mins;
    private TextView airingin;
    public Spinner filter;
    public ImageView download;
    public Bundle bundle;
    
    List<Post> posts; 
    List<Event> events;
    List<Event> onstage_events;
    List<Event> offstage_events;
    List<String> all_event_names;
    List<School> schools;
    List<SchEvent> schedule;
    
	//RequestQueue queue;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        filter = (Spinner) findViewById(R.id.filter);
        download = (ImageView) findViewById(R.id.repdownload);
        
        bundle = getIntent().getExtras();
        
        posts = (List<Post>) bundle.getSerializable("post_list");
        events = (List<Event>) bundle.getSerializable("events");
        all_event_names = bundle.getStringArrayList("events_list");
        onstage_events = (List<Event>) bundle.getSerializable("onstage");
        offstage_events = (List<Event>) bundle.getSerializable("offstage");
        schools = (List<School>) bundle.getSerializable("schools");
        schedule = (List<SchEvent>) bundle.getSerializable("schedule");
        
        if (posts.size() == 0) Toast.makeText(this, "Switch on Internet to download posts.", Toast.LENGTH_LONG).show();
        
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getSupportActionBar().getThemedContext(), android.R.layout.simple_spinner_item, all_event_names);
        
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.filter_item);
        
        // attaching data adapter to spinner
        filter.setAdapter(dataAdapter);
        
        /*List<String> events = new ArrayList<String>();
        events.add("All");
        events.add("Beat");
        events.add("Tango");
        events.add("Raag");
        events.add("Nritya");
        events.add("Shutter"); */
        
        // Creating adapter for spinner
        
        download.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(11)
			@Override
			public void onClick(View arg0) {
				DownloadManager.Request r = new DownloadManager.Request(Uri.parse("http://boscofest2016.com/RepProfile.pdf"));

				// This put the download in the same Download dir the browser uses
				r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Rep Profile.pdf");

				// When downloading music and videos they will be listed in the player
				// (Seems to be available since Honeycomb only)
				if (android.os.Build.VERSION.SDK_INT >= 11) {
					r.allowScanningByMediaScanner();
					
					// Notify user when download is completed
					// (Seems to be available since Honeycomb only)
					r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				}
			    
				// Start download
				DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
				dm.enqueue(r);
				Toast.makeText(MainActivity.this, "Downloading Rep's Profile...", Toast.LENGTH_SHORT).show();
			}
		});
        
        filter.setVisibility(View.GONE);
        download.setVisibility(View.GONE);
        
        
        options = new String[] {"The Fiesta","Events","Winners","Schedule","Schools","Updates","Our Team","Sponsors","About Us"};
        int[] icons = {R.drawable.ic_action_fiesta, R.drawable.ic_action_events, R.drawable.ic_action_events,
        		R.drawable.ic_action_schedule, R.drawable.ic_action_schools,
        		R.drawable.ic_action_updates, R.drawable.ic_action_participants, R.drawable.ic_action_sponsors, R.drawable.ic_action_about};
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerRLayout = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        countdown_days = (TextView) findViewById(R.id.countdown_days);
        countdown_hours = (TextView) findViewById(R.id.countdown_hours);
        countdown_mins = (TextView) findViewById(R.id.countdown_minutes);
        airingin = (TextView) findViewById(R.id.airingin);
        
        ticktock = new CountDownTimer(1000, 1000) {
        	public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                long festDate = 1468636200l;
                long now = System.currentTimeMillis()/1000;
                long diff = festDate - now;
                
                if (diff > 0) {
	                int days = (int) diff/(86400);
	                int hours = (int)(diff - days*86400)/3600;
	                int mins = (int)(diff - days*86400 - hours*3600)/60;
	                int seconds = (int)(diff - days*86400 - hours*3600 - mins*60);
	                
	                if (days > 0) {
	                countdown_days.setText(days+"\nDays");
	                countdown_hours.setText(hours+"\nHrs");
	                countdown_mins.setText(mins+"\nMin");
	                } else {
	                	countdown_days.setText(hours+"\nHrs");
	                    countdown_hours.setText(mins+"\nMin");
	                    countdown_mins.setText(seconds+"\nSec");
	                }
	                ticktock.start();
                } else {
                	if (diff*-1/86400 > 1)
                		airingin.setText("---");
                	else
                		airingin.setText("On Air");
                	
                	countdown_days.setText("-\nHrs");
                    countdown_hours.setText("-\nMin");
                    countdown_mins.setText("-\nSec");
                }
            }
        }.start();

        // Set the adapter for the list view
        listAdapter = new DrawerAdapter(this, options, icons, 0);
        mDrawerList.setAdapter(listAdapter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (this, 
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        
        mDrawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        /*filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        		String event = "";
        		switch (pos) {
        			case 0: break;
        			case 1: event = "Beat"; break;
        			case 2: event = "Tango"; break;
        			case 3: event = "Raag"; break;
        			case 4: event = "Nritya"; break;
        			case 5: event = "Shutter"; break;
        		}
        		
        		getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new Updates(filter(event)))
                .commit();
        	  }

        	  @Override
        	  public void onNothingSelected(AdapterView<?> arg0) {
        		// TODO Auto-generated method stub
        	  }
        }); */
        
        // Read posts
        
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, new Fiesta())
        .commit();
        setTitle(options[0]);
        
        // Start notification service.
        //Intent intent = new Intent(this, Updater.class);  
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(  
          //                            this.getApplicationContext(), 0, intent, 0);  
        //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);  
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()  
                                      //+300000, 300000, pendingIntent);  
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	Fragment fragment;
	    	
	    	switch (position) {
	    	case 1: fragment = new Events(onstage_events, offstage_events, bundle); break;
	    	case 2: fragment = new Winners(); break;
	    	case 3: fragment = new Schedule(schedule); break;
	    	case 4: fragment = new Schools(schools); break;
	    	case 5: fragment = new Updates();
	    			bundle.putInt("event", 0);
	    			bundle.putBoolean("toggle", true);
	    			fragment.setArguments(bundle);
	    			break;
	    	case 6: fragment = new OurTeam(); break;
	    	case 7: fragment = new Sponsors(); break;
	    	case 8: fragment = new AboutUs(); break;
	    	default: fragment = new Fiesta(); break;
	    	}
	    	
	    	getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	    	
	        // Insert the fragment by replacing any existing fragment
	        getSupportFragmentManager().beginTransaction()
	                       .replace(R.id.content_frame, fragment)
	                       .commit();
	        setTitle(options[position]);
	        
	        filter.setVisibility(View.GONE);
	    	filter.setOnItemSelectedListener(null);
	    	filter.setSelection(0);
	    	
	    	download.setVisibility(View.GONE);
	        listAdapter.setSelection(position);
	        mDrawerLayout.closeDrawer(mDrawerRLayout);
	        
	    }
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);    
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	boolean doubleBackToExitPressedOnce = false;

	Toast backmessage = null;
	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
	    if (doubleBackToExitPressedOnce) {
	        super.onBackPressed();
	        if (backmessage != null) backmessage.cancel();
	        finish();
	        return;
	    }
	    System.out.println(doubleBackToExitPressedOnce);

	    doubleBackToExitPressedOnce = true;
	    backmessage = Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT);
	    backmessage.show();

	    new Handler().postDelayed(new Runnable() {
	        @Override
	        public void run() {
	            doubleBackToExitPressedOnce=false;                       
	        }
	    }, 2000);
		} else super.onBackPressed();
	}
	
	@SuppressLint("NewApi")
	public View onCreateView(View parent, String name, Context context, AttributeSet attrs)
	{
	    if(Build.VERSION.SDK_INT >= 11)
	      return super.onCreateView(parent, name, context, attrs);
	    return null;
	}
}
