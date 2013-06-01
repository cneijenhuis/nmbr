package com.baseapp.eyeem.androidsdk.models;


import java.io.Serializable;

import java.util.HashMap;
import java.util.Vector;




public class CurrentUser extends EyeemUser implements Serializable {
	private static final long serialVersionUID = 7526471155622776152L;

	public Vector<Integer> followerIds, friendIds, likedPhotoIds;
	public String email="";
	public HashMap<String,Integer> services = new HashMap<String,Integer>();
	public HashMap<String,Boolean> settings = new HashMap<String, Boolean>();
	
	public boolean emailNotifications=false;
	public boolean facebookPrimary = false, facebookPostAlbum = false, facebookPhotoLike = false, facebookPhotoDiscover = false, facebookPhotoComment = false, facebookAlbumLike = false, facebookUserFollow = false, showedfacebookTimelineDialog=false;
	
	public String access_token="";
	
	public boolean isNew;

	public CurrentUser() {
		services.put("facebook", 0);
		services.put("twitter", 0);
		services.put("flickr", 0);
		services.put("foursquare", 0);
		services.put("tumblr", 0);
		
		settings.put("push_photo_like",false);
		settings.put("push_photo_comment",false);
		settings.put("push_user_follower",false);
		settings.put("push_user_joined",false);
		settings.put("push_album_contributor",false);
		settings.put("push_photo_comment_mention",false);
		settings.put("email_photo_like",false);
		settings.put("email_photo_comment",false);
		settings.put("email_user_follower",false);
		settings.put("email_user_joined",false);
		settings.put("email_album_contributor",false);
		settings.put("email_photo_comment_mention",false);
		
		followerIds = new Vector<Integer>();
		friendIds = new Vector<Integer>();
		likedPhotoIds = new Vector<Integer>();
	}
	
	public CurrentUser(int userId, String fullname, String thumbUrl) {
		this();
		this.fullname = fullname;
		this.userId = userId;
		this.thumbUrl = thumbUrl;

	}
	
	public boolean isFollower(int userId){
		return followerIds.contains(userId);
	}
	
	public boolean hasLikedPhoto(int photoId){
		return likedPhotoIds.contains(photoId);
	}	
	public boolean isFriend(int userId){
		return friendIds.contains(userId);
	}
		
}
