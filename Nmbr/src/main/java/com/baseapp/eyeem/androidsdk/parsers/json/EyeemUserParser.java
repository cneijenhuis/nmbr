package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemUser;

public class EyeemUserParser extends EyeemAbstractParser<EyeemUser>{

	@Override
	public EyeemUser parse(JSONObject user) throws JSONException {
		EyeemUser obj = new EyeemUser();
		
		if (user.has("user")) {
			user = user.getJSONObject("user");
	    }
		
	    if (user.has("id")) {
	        obj.userId = user.getInt("id");
	    } else{
	    	return null;
	    }
	    if (user.has("fullname")) {
	        obj.fullname = user.getString("fullname");
	    }
	    if (user.has("nickname") && !user.isNull("nickname")) {
	        obj.nickname = user.getString("nickname");
	    } 

	    if (user.has("thumbUrl")) {
	        obj.thumbUrl = user.getString("thumbUrl");
	        if(obj.thumbUrl.startsWith("http://apitest.eyeem.com/thumb/h/100/")){
	        	obj.photofilename = obj.thumbUrl.substring(37);
	        }else{
	        	obj.photofilename = obj.thumbUrl.substring(33);
	        }
	    }
	    
	    if (user.has("photoUrl")) {
	        obj.photoUrl = user.getString("photoUrl");
	    }  
	    if (user.has("description") && !user.isNull("description")) {
	        obj.description = user.getString("description");
	    }
	    if (user.has("totalPhotos")) {
	        obj.totalPhotos = user.getInt("totalPhotos");
	    }  
	    if (user.has("totalFollowers")) {
	        obj.totalFollowers = user.getInt("totalFollowers");
	    }
	    if (user.has("totalFriends")) {
	        obj.totalFriends = user.getInt("totalFriends");
	    }
	    
	    if (user.has("totalPhotos")) {
	        obj.totalPhotosContributed = user.getInt("totalPhotos");
	    }
	    return obj;
	}
	public ArrayList<EyeemUser> parseUsers(JSONObject json) throws JSONException{
		ArrayList<EyeemUser> mEyeemUsers = new ArrayList<EyeemUser>();
		JSONArray users = null;
		if(json.has("friends")){
			json = json.getJSONObject("friends");
		} else if(json.has("followers")){
			json = json.getJSONObject("followers");
		} else if(json.has("likers")){
			json = json.getJSONObject("likers");
		} else if(json.has("contributors")){
			json = json.getJSONObject("contributors");
		}else if(json.has("users")){
			json = json.getJSONObject("users");
		}
		
		if(json.has("contacts")){
			users = json.getJSONArray("contacts");
		}
		
		if(json.has("items")){
			users = json.getJSONArray("items");
		}
		
		for(int i=0;i<users.length();i++){
			mEyeemUsers.add(parse(users.getJSONObject(i)));
		}
		return mEyeemUsers;
	}
}