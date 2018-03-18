package com.boscofest.fest16;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Update extends Fragment {
	Post post;
	
	Update(Post post) {
		this.post = post;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_update, parent, false);
        
        ExpandableHeightGridView image_gallery = (ExpandableHeightGridView) view.findViewById(R.id.image_gallery);
        ExpandableHeightGridView video_gallery = (ExpandableHeightGridView) view.findViewById(R.id.video_gallery);
        LinearLayout images_section = (LinearLayout) view.findViewById(R.id.images_section);
        LinearLayout videos_section = (LinearLayout) view.findViewById(R.id.videos_section);
        
        TextView content = (TextView) view.findViewById(R.id.post_content);
        TextView time = (TextView) view.findViewById(R.id.post_time);
        
        ImageLoader listAdapter = new ImageLoader(getContext(), post.images);
        VideoLoader videoAdapter = new VideoLoader(getContext(), post.videos);
        image_gallery.setAdapter(listAdapter);
        image_gallery.setExpanded(true);
        video_gallery.setAdapter(videoAdapter);
        video_gallery.setExpanded(true);
        listAdapter.notifyDataSetChanged();
        videoAdapter.notifyDataSetChanged();
        
        if (post.images.size() == 0) images_section.setVisibility(View.GONE);
        if (post.videos.size() == 0) videos_section.setVisibility(View.GONE);
        
        content.setText(post.getContent());
        time.setText(post.timeToString());
        
        return view;
	}
	
	public void onBackPressed() {
		getActivity().getSupportFragmentManager().popBackStack();
	}
}
