package com.boscofest.fest16;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ImageGalleryAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> images;

    public ImageGalleryAdapter(Context context, List<String> images) {
        mContext = context;
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        TouchImageView imageView = new TouchImageView(mContext);
        Picasso.with(mContext).load("http://boscofest2016.com/app/images/"+images.get(position)+".jpg").placeholder(R.drawable.loadimg).into(imageView);
        collection.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}