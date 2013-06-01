package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.baseapp.eyeem.androidsdk.models.EyeemComment;
import com.baseapp.eyeem.androidsdk.models.EyeemUser;

public class EyeemCommentParser extends EyeemAbstractParser<EyeemComment>{

	@Override
	public EyeemComment parse(JSONObject comment) throws JSONException {
		EyeemUserParser mEyeemUserParser = new EyeemUserParser();
		EyeemComment obj = new EyeemComment();
		
		if (comment.has("comment")) {
			comment = comment.getJSONObject("comment");
	    }
		
	    if (comment.has("id")) {
	        obj.commentId = comment.getInt("id");
	    } else{
	    	return null;
	    }
	    if (comment.has("message")) {
	        obj.message = comment.getString("message");
	    }
	    
	    if (comment.has("photoId")) {
	        obj.photoId = comment.getInt("photoId");
	    }
	    
	    if (comment.has("extendedMessage")) {
	        obj.extendedMessage = comment.getString("extendedMessage");
	    }
	    
	    if (comment.has("user")) {
	    	obj.author = mEyeemUserParser.parse(comment.getJSONObject("user"));
	    }
	    
	    if (comment.has("mentionedUsers")) {
	    	obj.mentionedUsers = new ArrayList<EyeemUser>();
	    	JSONArray users = comment.getJSONArray("mentionedUsers");
	    	for (int i = 0; i < users.length(); i++) {
				obj.mentionedUsers.add(mEyeemUserParser.parse(users.getJSONObject(i)));
			}
	    }
	    
	    if(comment.has("updated")){
	    	obj.updated = DateToMilli(comment.getString("updated"));
	    }	    
	    return obj;
	}
	public ArrayList<EyeemComment> parseComments(JSONObject json) throws JSONException{
		ArrayList<EyeemComment> mEyeemComment = new ArrayList<EyeemComment>();
		JSONArray comments;
		if(json.has("comments")){
			json = json.getJSONObject("comments");
		}
		if(json.has("items")){
			comments = json.getJSONArray("items");
			for(int i=0;i<comments.length();i++){
				mEyeemComment.add(parse(comments.getJSONObject(i)));
			}
		}
		return mEyeemComment;
	}
}