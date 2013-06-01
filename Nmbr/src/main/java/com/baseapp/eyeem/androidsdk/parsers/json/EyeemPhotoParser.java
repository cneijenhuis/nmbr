package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemAlbum;
import com.baseapp.eyeem.androidsdk.models.EyeemComment;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.models.EyeemUser;

public class EyeemPhotoParser extends EyeemAbstractParser<EyeemPhoto>{
	
	@Override
	public EyeemPhoto parse(JSONObject photo) throws JSONException {
		EyeemPhoto obj = new EyeemPhoto();
		if (photo.has("photo")) {
			photo = photo.getJSONObject("photo");
	    }
		
	    if (photo.has("id")) {
	        obj.photoId = photo.getInt("id");
	    } else{
	    	return null;
	    }
	    if (photo.has("caption")) {
	        obj.caption = photo.getString("caption");
	    }
	    if (photo.has("title")) {
	        obj.title = photo.getString("title");
	    }	    
	    if (photo.has("width")) {
	        obj.width = photo.getInt("width");
	    }	    
	    if (photo.has("height")) {
	        obj.height = photo.getInt("height");
	    }	    	    
	    if (photo.has("thumbUrl")) {
	        obj.thumbUrl = photo.getString("thumbUrl");
	        if(obj.thumbUrl.startsWith("http://apitest.eyeem.com/thumb/h/100/")){
	        	obj.filename = obj.thumbUrl.substring(37);
	        }else{
	        	obj.filename = obj.thumbUrl.substring(33);
	        }
	    }
	    if (photo.has("photoUrl")) {
	        obj.photoUrl = photo.getString("photoUrl");
	    } 
	    if(photo.has("updated")){
	    	obj.updatedString = photo.getString("updated");
	    	obj.updated = DateToMilli(obj.updatedString);
	    }
	    
	    if(photo.has("user")){
	    	JSONObject user = photo.getJSONObject("user");
	    	EyeemUserParser parser = new EyeemUserParser();
	    	obj.user = parser.parse(user);
	    }
	    
	    if(photo.has("albums")){
	    	JSONObject albumsObject = photo.getJSONObject("albums");
	    	EyeemAlbumParser parser = new EyeemAlbumParser();
	    	obj.albums = new Vector<EyeemAlbum>();
	    	obj.albums.addAll(parser.parseAlbums(albumsObject));
	    }
	    
	    if(photo.has("comments") && photo.has("totalComments")){
	    	obj.totalComments = photo.getInt("totalComments");
	    	JSONObject comments = photo.getJSONObject("comments");
	    	EyeemCommentParser parser = new EyeemCommentParser();
	    	obj.comments = new Vector<EyeemComment>();
	    	obj.comments.addAll(parser.parseComments(comments));
	    }
	    
	    if(photo.has("likers") && photo.has("totalLikes")){
	    	obj.totalLikes = photo.getInt("totalLikes");
	    	JSONObject likers = photo.getJSONObject("likers");
	    	EyeemUserParser parser = new EyeemUserParser();
	    	obj.likers = new Vector<EyeemUser>();
	    	obj.likers.addAll(parser.parseUsers(likers));
	    }
	    
	    
	    //extend to parse likers and comments!
	    //and albums?
	    return obj;
	}
	public ArrayList<EyeemPhoto> parsePhotos(JSONObject json) throws JSONException{
		ArrayList<EyeemPhoto> mEyeemPhoto = new ArrayList<EyeemPhoto>();
		JSONArray photos;
		if(json.has("photos")){
			json = json.getJSONObject("photos");
		}
		if(json.has("friendsPhotos")){
			json = json.getJSONObject("friendsPhotos");
		}
		if(json.has("items")){
			photos = json.getJSONArray("items");
			for(int i=0;i<photos.length();i++){
				mEyeemPhoto.add(parse(photos.getJSONObject(i)));
			}
		}
		return mEyeemPhoto;
	}
}