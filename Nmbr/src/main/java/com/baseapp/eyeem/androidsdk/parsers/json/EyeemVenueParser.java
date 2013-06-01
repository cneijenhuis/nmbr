package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.Venue;

public class EyeemVenueParser{

	public static Venue parseVenue(JSONObject item) throws JSONException{
		Venue v = new Venue();
		if(item.has("venue")){
			item = item.getJSONObject("venue");
			if (item.has("serviceId")) {
				v.venueId = item.getString("serviceId");
			}
			if(item.has("name")){
				v.venueName = item.getString("name");
			}
			if(item.has("location")){
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
			} else{
				if(item.has("lat")){
					v.venueSpecs.put("lat", item.getString("lat"));
				}
				if(item.has("lng")){
					v.venueSpecs.put("lng", item.getString("lng"));
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