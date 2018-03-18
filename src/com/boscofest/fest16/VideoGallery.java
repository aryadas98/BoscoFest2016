package com.boscofest.fest16;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoGallery extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String video = getIntent().getStringExtra("video");
        
        setContentView(R.layout.video_page);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video_link = Uri.parse("http://boscofest2016.com/app/videos/"+video+".mp4");
        
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video_link);
        videoView.start();
        
        // set Listeners
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            	ProgressBar loading = (ProgressBar) findViewById(R.id.loading_spinner);
            	loading.setVisibility(View.GONE);
            }
        });
        
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
            	AlertDialog.Builder builder = new AlertDialog.Builder(VideoGallery.this);
            	builder.setMessage("There was a problem playing the video.")
            	       .setCancelable(false)
            	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            	           public void onClick(DialogInterface dialog, int id) { finish(); }
            	       });
            	AlertDialog alert = builder.create();
            	alert.show();
            	return true;
            }
        });
        
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				finish();
			}
		});

    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    
}