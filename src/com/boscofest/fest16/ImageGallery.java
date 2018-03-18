package com.boscofest.fest16;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ImageGallery extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        List<String> images = getIntent().getStringArrayListExtra("imageList");
        int pos = getIntent().getIntExtra("pos", 0);
        
        setContentView(R.layout.activity_gallery);
        ExtendedViewPager gallery = (ExtendedViewPager) findViewById(R.id.pager);
        gallery.setAdapter(new ImageGalleryAdapter((Context)this, images));
        gallery.setCurrentItem(pos);
    }
    
}