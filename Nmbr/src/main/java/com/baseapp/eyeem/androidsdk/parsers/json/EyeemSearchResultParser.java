package com.baseapp.eyeem.androidsdk.parsers.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemAlbum;
import com.baseapp.eyeem.androidsdk.models.EyeemSearchResult;
import com.baseapp.eyeem.androidsdk.models.EyeemUser;

public class EyeemSearchResultParser extends EyeemAbstractParser<EyeemSearchResult>{

	public EyeemAlbum parseAlbum(JSONObject album) throws JSONException {
		return new EyeemAlbumParser().parse(album);
	}
	
	public EyeemUser parseUser(JSONObject user) throws JSONException {
		return new EyeemUserParser().parse(user);
	}
	
	public EyeemSearchResult parseSearchResult(JSONObject json) throws JSONException{
		EyeemSearchResult result = new EyeemSearchResult();
		
		JSONObject obj = json.getJSONObject("users");
		result.totalUsers = obj.getInt("total");
		if (obj.has("items")) {
			JSONArray users = obj.getJSONArray("items");
			for (int i = 0; i < users.length(); i++) {
				JSONObject item = users.getJSONObject(i);
				result.users.add(parseUser(item));
			}
		}
		
		obj = json.getJSONObject("albums");
		result.totalAlbums = obj.getInt("total");
		if (obj.has("items")) {
			JSONArray users = obj.getJSONArray("items");
			for (int i = 0; i < users.length(); i++) {
				JSONObject item = users.getJSONObject(i);
				result.albums.add(parseAlbum(item));
			}
		}
		
		
		return result;
	}

	@Override
	public EyeemSearchResult parse(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
}