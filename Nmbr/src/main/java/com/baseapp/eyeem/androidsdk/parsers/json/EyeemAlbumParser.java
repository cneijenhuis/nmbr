package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemAlbum;

public class EyeemAlbumParser extends EyeemAbstractParser<EyeemAlbum>{

	@Override
	public EyeemAlbum parse(JSONObject album) throws JSONException {
		EyeemAlbum obj = new EyeemAlbum();
		EyeemPhotoParser mEyeemPhotos = new EyeemPhotoParser();
		if (album.has("album")) {
			album = album.getJSONObject("album");
	    }
		
	    if (album.has("id")) {
	        obj.albumId = album.getString("id");
	    } else{
	    	return null;
	    }
	    if (album.has("name")) {
	        obj.name = album.getString("name");
	    }
	    
	    if (album.has("specialType")) {
	        obj.specialType = album.getString("specialType");
	    }	    
	    if (album.has("thumbUrl")) {
	        obj.thumbUrl = album.getString("thumbUrl");
	        if(obj.thumbUrl.startsWith("http://apitest.eyeem.com/thumb/h/100/")){
	        	obj.filename = obj.thumbUrl.substring(37);
	        }else{
	        	obj.filename = obj.thumbUrl.substring(34);
	        }
	    }
	    if (album.has("type")) {
	        obj.type = album.getString("type");
	    }
	    if (album.has("totalPhotos")) {
	        obj.totalPhotos = album.getInt("totalPhotos");
	    }
	    if (album.has("totalLikers")) {
	        obj.totalLikers = album.getInt("totalLikers");
	    }    
	    if (album.has("totalContributors")) {
	        obj.totalContributors = album.getInt("totalContributors");
	    }
	    if(album.has("photos")){
	    	obj.albumPhotos = mEyeemPhotos.parsePhotos(album.getJSONObject("photos"));
	    }
	    if(album.has("updated")){
	    	obj.updated = DateToMilli(album.getString("updated"));
	    }
	    
	    if(album.has("location")){
	    	JSONObject location = album.getJSONObject("location");
	    	
	    	if (location.has("latitude")) {
		        obj.lat = location.getString("latitude");
		    }
		    
		    if (location.has("longitude")) {
		        obj.lng = location.getString("longitude");
		    }
	    	
	    	if(location.has("cityAlbum"))
	    		obj.cityAlbum = this.parse(location.getJSONObject("cityAlbum"));
	    	
	    	if(location.has("countryAlbum"))
	    		obj.countryAlbum = this.parse(location.getJSONObject("countryAlbum"));
	    	
	    	if(location.has("venueService")){
	    		JSONObject venueService = location.getJSONObject("venueService");
	    		if(venueService.has("categoryName"))
	    			obj.categoryName = venueService.getString("categoryName");
	    	}
	    }
	    
	    obj.hidden = album.optBoolean("hidden");
	    
	    return obj;
	}
	public ArrayList<EyeemAlbum> parseAlbums(JSONObject json) throws JSONException{
		ArrayList<EyeemAlbum> mEyeemAlbum = new ArrayList<EyeemAlbum>();
		JSONArray albums;
		if(json.has("likedAlbums")){
			json = json.getJSONObject("likedAlbums");
		} else if(json.has("feedAlbums")){
			json = json.getJSONObject("feedAlbums");
		} else if(json.has("albums")){
			json = json.getJSONObject("albums");
		}
		if(json.has("items")){
			albums = json.getJSONArray("items");
			for(int i=0;i<albums.length();i++){
				mEyeemAlbum.add(parse(albums.getJSONObject(i)));
			}
		}
		return mEyeemAlbum;
	}
}