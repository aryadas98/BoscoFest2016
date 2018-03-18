package com.boscofest.fest16;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	int id; String content; int event; Date date;
	List<String> images, videos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
	
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public String timeToString() {
		System.out.println((new Date().getTime() - date.getTime())/(86400 * 1000));
		if ((new Date().getTime() - date.getTime())/(86400 * 1000) > 0) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
		return sdf.format(date);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
			return sdf.format(date);
		}
	}
	
	public void setTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
		this.date = sdf.parse(date);
		} catch (Exception e) {}
	}
	public String getImages() {
		String images = "";
		for(int i=0; i<this.images.size(); i++)
			images+=this.images.get(i)+",";
		return images;
	}
	
	public List<String> imageList() {
		return images;
	}
	
	public void setImages(String images) {
		this.images = new ArrayList<String>();
		Scanner sc = new Scanner(images);
		sc.useDelimiter(",");
		while(sc.hasNext())
			this.images.add(sc.next());
		sc.close();
	}
	public String getVideos() {
		String videos = "";
		for(int i=0; i<this.videos.size(); i++)
			videos+=this.videos.get(i)+",";
		return videos;
	}
	
	public List<String> videoList() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = new ArrayList<String>();
		Scanner sc = new Scanner(videos);
		sc.useDelimiter(",");
		while(sc.hasNext())
			this.videos.add(sc.next());
		sc.close();
	}
}
