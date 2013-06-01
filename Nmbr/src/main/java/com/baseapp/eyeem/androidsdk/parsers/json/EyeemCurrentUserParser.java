package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.baseapp.eyeem.androidsdk.models.CurrentUser;

public class EyeemCurrentUserParser extends EyeemAbstractParser<CurrentUser>{

	@Override
	public CurrentUser parse(JSONObject json) throws JSONException{
		return parse(new CurrentUser(),json);
	}
	public CurrentUser parse(CurrentUser obj, JSONObject json) throws JSONException {
		obj.isNew = json.optBoolean("new");
		
		if (json.has("access_token")) {
            obj.access_token = json.getString("access_token");
            json = json.getJSONObject("user");
        }
        
        if(json.has("user")){
        	json = json.getJSONObject("user");
        }
        if (json.has("id")) {
            obj.userId = json.getInt("id");
        } 
        if (json.has("fullname")) {
            obj.fullname = json.getString("fullname");
        } 
        if (json.has("nickname") && !json.isNull("nickname")) {
            obj.nickname = json.getString("nickname");
        } else{
        	obj.nickname = null;
        }
        if (json.has("thumbUrl")) {
            obj.thumbUrl = json.getString("thumbUrl");
        }   
        if (json.has("photoUrl")) {
            obj.photoUrl = json.getString("photoUrl");
        }
        if (json.has("email")) {
            obj.email = json.getString("email");
        }    
        if (json.has("description") && !json.isNull("description")) {
            obj.description = json.getString("description");
        } else{
        	obj.description = null;
        }
        if (json.has("emailNotifications")) {
            obj.emailNotifications = json.getBoolean("emailNotifications");
        }       
        if (json.has("totalPhotos")) {
            obj.totalPhotos = json.getInt("totalPhotos");
        }  
        if (json.has("totalFollowers")) {
            obj.totalFollowers = json.getInt("totalFollowers");
        }
        if (json.has("totalFriends")) {
            obj.totalFriends = json.getInt("totalFriends");
        }
        
        if (json.has("newsSettings")) {
        	JSONObject newsSettings = json.getJSONObject("newsSettings");
        	obj.settings = new HashMap<String, Boolean>();
        	
        	obj.settings.put("push_photo_like",newsSettings.getBoolean("push_photo_like"));
        	obj.settings.put("push_photo_comment",newsSettings.getBoolean("push_photo_comment"));
        	obj.settings.put("push_user_follower",newsSettings.getBoolean("push_user_follower"));
        	obj.settings.put("push_user_joined",newsSettings.getBoolean("push_user_joined"));
        	obj.settings.put("push_album_contributor",newsSettings.getBoolean("push_album_contributor"));
        	obj.settings.put("push_photo_comment_mention",newsSettings.getBoolean("push_photo_comment_mention"));
        	obj.settings.put("email_photo_like",newsSettings.getBoolean("email_photo_like"));
        	obj.settings.put("email_photo_comment",newsSettings.getBoolean("email_photo_comment"));
        	obj.settings.put("email_user_follower",newsSettings.getBoolean("email_user_follower"));
        	obj.settings.put("email_user_joined",newsSettings.getBoolean("email_user_joined"));
        	obj.settings.put("email_album_contributor",newsSettings.getBoolean("email_album_contributor"));
        	obj.settings.put("email_photo_comment_mention",newsSettings.getBoolean("email_photo_comment_mention"));
        }
        
        if(json.has("services")){
        	json = json.getJSONObject("services");
        	if(json.has("twitter")){
        		if(json.getJSONObject("twitter").getString("status").equals("active")){
	        		if(((Integer)obj.services.get("twitter")).intValue()==0){
	        			obj.services.put("twitter", 2);
	        		}
        		}else if(json.getJSONObject("twitter").getString("status").equals("inactive")){
        			obj.services.put("twitter", 0);
        		}
        	}
        	if(json.has("facebook")){
        		if(json.getJSONObject("facebook").getString("status").equals("active")){
	        		if(((Integer)obj.services.get("facebook")).intValue()==0){
	        			obj.services.put("facebook", 2);
	        		}
        		}else if(json.getJSONObject("facebook").getString("status").equals("inactive")){
        			obj.services.put("facebook", 0);
        		}
        		if(json.getJSONObject("facebook").has("primary")){
        			if(json.getJSONObject("facebook").getString("primary").equals("true")){
            			obj.facebookPrimary = true;
            		}else{
            			obj.facebookPrimary = false;
            		}
        		}else{
        			obj.facebookPrimary = false;
        		}        		
        		if(json.getJSONObject("facebook").has("upload")){
        			if(json.getJSONObject("facebook").getString("upload").equals("true")){
            			obj.facebookPostAlbum = true;
            		}else{
            			obj.facebookPostAlbum = false;
            		}
        		}else{
        			obj.facebookPostAlbum = false;
        		}
        		if(json.getJSONObject("facebook").has("photolike")){
        			if(json.getJSONObject("facebook").getString("photolike").equals("true")){
            			obj.facebookPhotoLike = true;
            		}else{
            			obj.facebookPhotoLike = false;
            		}
        		}else{
        			obj.facebookPhotoLike = false;
        		}
        		if(json.getJSONObject("facebook").has("photodiscover")){
        			if(json.getJSONObject("facebook").getString("photodiscover").equals("true")){
            			obj.facebookPhotoDiscover = true;
            		}else{
            			obj.facebookPhotoDiscover = false;
            		}
        		}else{
        			obj.facebookPhotoDiscover = false;
        		}
        		if(json.getJSONObject("facebook").has("timelinepopup")){
        			if(json.getJSONObject("facebook").getString("timelinepopup").equals("true")){
            			obj.showedfacebookTimelineDialog = true;
            		}else{
            			obj.showedfacebookTimelineDialog = false;
            		}
        		}else{
        			obj.showedfacebookTimelineDialog = false;
        		}
        		
        		if(json.getJSONObject("facebook").has("photocomment")){
        			if(json.getJSONObject("facebook").getString("photocomment").equals("true")){
            			obj.facebookPhotoComment = true;
            		}else{
            			obj.facebookPhotoComment = false;
            		}
        		}else{
        			obj.facebookPhotoComment = false;
        		}   
        		if(json.getJSONObject("facebook").has("albumlike")){
        			if(json.getJSONObject("facebook").getString("albumlike").equals("true")){
            			obj.facebookAlbumLike = true;
            		}else{
            			obj.facebookAlbumLike = false;
            		}
        		}else{
        			obj.facebookAlbumLike = false;
        		}  
        		if(json.getJSONObject("facebook").has("userfollow")){
        			if(json.getJSONObject("facebook").getString("userfollow").equals("true")){
            			obj.facebookUserFollow = true;
            		}else{
            			obj.facebookUserFollow = false;
            		}
        		}else{
        			obj.facebookUserFollow = false;
        		}         		
        	}
        	if(json.has("flickr")){
        		if(json.getJSONObject("flickr").getString("status").equals("active")){
	        		if(((Integer)obj.services.get("flickr")).intValue()==0){
	        			obj.services.put("flickr", 2);
	        		}
        		}else if(json.getJSONObject("flickr").getString("status").equals("inactive")){
        			obj.services.put("flickr", 0);
        		}
        	}
        	if(json.has("foursquare")){
        		if(json.getJSONObject("foursquare").getString("status").equals("active")){
	        		if(((Integer)obj.services.get("foursquare")).intValue()==0){
	        			obj.services.put("foursquare", 2);
	        		}
        		}else if(json.getJSONObject("foursquare").getString("status").equals("inactive")){
        			obj.services.put("foursquare", 0);
        		}
        	}
        	if(json.has("tumblr")){
        		if(json.getJSONObject("tumblr").getString("status").equals("active")){
	        		if(((Integer)obj.services.get("tumblr")).intValue()==0){
	        			obj.services.put("tumblr", 2);
	        		}
        		}else if(json.getJSONObject("tumblr").getString("status").equals("inactive")){
        			obj.services.put("tumblr", 0);
        		}
        	}
        }
        return obj;
	}
	public CurrentUser parseFollowerIds(CurrentUser obj, JSONObject json) throws JSONException {
		JSONArray userIds = json.getJSONArray("userIds");
		
		for(int i=0;i<userIds.length();i++){
			obj.followerIds.add(userIds.getInt(i));
		}
		
		return obj;
	}
	public CurrentUser parseFriendIds(CurrentUser obj, JSONObject json) throws JSONException {
		JSONArray userIds = json.getJSONArray("userIds");
		obj.friendIds.clear();
		for(int i=0;i<userIds.length();i++){
			obj.friendIds.add(userIds.getInt(i));
		}
		return obj;
	}	
		
	public CurrentUser parseLikedPhotoIds(CurrentUser obj, JSONObject json) throws JSONException {
		JSONArray photoIds = json.getJSONArray("photoIds");
		
		for(int i=0;i<photoIds.length();i++){
			obj.likedPhotoIds.add(photoIds.getInt(i));
		}
		
		return obj;
	}			
	public ArrayList<CurrentUser> parse(JSONArray array) throws JSONException{
		return null;
	}
}
