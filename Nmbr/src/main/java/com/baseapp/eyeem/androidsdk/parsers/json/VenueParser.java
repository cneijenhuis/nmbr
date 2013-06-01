package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.Venue;

public class VenueParser{

	public static Venue parseVenue(JSONObject item) throws JSONException{
		Venue v = new Venue();
		if (item.has("id")) {
			v.venueId = item.getString("id");
		}
		v.venueName = item.getString("name");
		if (item.getJSONObject("location").has("city")) {
			v.venueSpecs.put("city", item.getJSONObject(
					"location").getString("city"));
		}
		if (item.getJSONObject("location").has("country")) {
			v.venueSpecs.put("country", item.getJSONObject(
					"location").getString("country"));
		}
		if (item.getJSONObject("location").has("lat")) {
			v.venueSpecs.put("lat", item.getJSONObject(
					"location").getString("lat"));
		}
		if (item.getJSONObject("location").has("lng")) {
			v.venueSpecs.put("lng", item.getJSONObject(
					"location").getString("lng"));
		}	
		if(item.has("categories")){
			JSONArray venueCategories = item.getJSONArray("categories");
			for(int k=0;k<venueCategories.length();k++){
				if(!v.categories.contains(venueCategories.getJSONObject(k).getString("pluralName"))){
					v.categories.add(venueCategories.getJSONObject(k).getString("pluralName"));
				}
				if(item.getJSONArray("categories").getJSONObject(k).has("parents")){
					JSONArray venueParents = item.getJSONArray("categories").getJSONObject(k).getJSONArray("parents");
					for(int l=0;l<venueParents.length();l++){
						if(!v.categories.contains(venueParents.getString(l))){
							v.categories.add(venueParents.getString(l));
						}
					}
				}
			}
		}
		return v;
	}
	
	public static Vector<Venue> parseVenues(JSONArray items) throws JSONException{
		Vector<Venue> data = null;
		if (items != null) {
			data = new Vector<Venue>();
			try {
				for (int j = 0; j < items.length(); j++) {
					JSONObject item = items.getJSONObject(j);
					data.add(parseVenue(item));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
		return data;
	}
}