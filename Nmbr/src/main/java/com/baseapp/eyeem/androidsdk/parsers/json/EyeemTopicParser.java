package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemTopic;

public class EyeemTopicParser extends EyeemAbstractParser<EyeemTopic>{

	@Override
	public EyeemTopic parse(JSONObject topic) throws JSONException {
		EyeemTopic obj = new EyeemTopic();
		if (topic.has("topic")) {
			topic = topic.getJSONObject("topic");
	    }
		
	    if (topic.has("id")) {
	        obj.topicId = topic.getString("id");
	    }
	    if (topic.has("name")) {
	        obj.name = topic.getString("name");
	    }
	    if (topic.has("totalPhotos")) {
	        obj.totalPhotos = topic.getInt("totalPhotos");
	    }	    
	    return obj;
	}
	public ArrayList<EyeemTopic> parseTopics(JSONObject json) throws JSONException{
		json=json.getJSONObject("topics");
		ArrayList<EyeemTopic> mEyeemTopic = new ArrayList<EyeemTopic>();
		JSONArray topics;
		if(json.has("items")){
			topics = json.getJSONArray("items");
			for(int i=0;i<topics.length();i++){
				mEyeemTopic.add(parse(topics.getJSONObject(i)));
			}
		}
//		if(json.has("topic")){
//			topics = json.getJSONArray("topic");
//			for(int i=0;i<topics.length();i++){
//				mEyeemTopic.add(parse(topics.getJSONObject(i)));
//			}
//		}
		return mEyeemTopic;
	}
}