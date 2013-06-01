package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.baseapp.eyeem.androidsdk.models.EyeemAlbum;
import com.baseapp.eyeem.androidsdk.models.EyeemComment;
import com.baseapp.eyeem.androidsdk.models.EyeemModelInterface;
import com.baseapp.eyeem.androidsdk.models.EyeemNewsItem;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.models.EyeemUser;

public class EyeemNewsParser extends EyeemAbstractParser<EyeemNewsItem>{

	@Override
	public EyeemNewsItem parse(JSONObject newsItem) throws JSONException {
		EyeemNewsItem obj = new EyeemNewsItem();
		if(newsItem.has("ids")){
			obj.hasAggregation = true;
			JSONObject aggregation = newsItem.getJSONObject("aggregation");
			obj.aggregationTotal = aggregation.getInt("total");
			obj.aggregationType = aggregation.getString("type");
			
			JSONArray ids = newsItem.getJSONArray("ids");
			obj.ids = new int[ids.length()];
			
			for (int i = 0; i < ids.length(); i++) {
				obj.ids[i] = ids.optInt(i);
			}
			
			EyeemAbstractParser<? extends EyeemModelInterface> aggregationParser = null;
			JSONArray aggregationArray = null;
			
			if(obj.aggregationType.equals("user")){
				obj.aggregationList = new ArrayList<EyeemAlbum>();
				aggregationParser = new EyeemUserParser();
				aggregationArray = aggregation.getJSONArray("users");
			}else if(obj.aggregationType.equals("photo")){
				obj.aggregationList = new ArrayList<EyeemPhoto>();
				aggregationParser = new EyeemPhotoParser();
				aggregationArray = aggregation.getJSONArray("photos");
			}else if(obj.aggregationType.equals("album")){
				obj.aggregationList = new ArrayList<EyeemAlbum>();
				aggregationParser = new EyeemAlbumParser();
				aggregationArray = aggregation.getJSONArray("albums");
			}
			
			for (int i = 0; i < aggregationArray.length(); i++) {
				
				try{
					JSONObject aggregationElement = aggregationArray.getJSONObject(i);
					aggregationParser.parse(aggregationElement);
				}catch (Exception e) {
					Log.e("EyeEm News Parser", "");
				}
				
			}
		}else if(newsItem.has("id")){
			obj.newsId = newsItem.getInt("id");
			obj.hasAggregation = false;
		}
		
		EyeemAbstractParser<? extends EyeemModelInterface> modelParser = null;
		
		if(newsItem.has("user")){
			modelParser = new EyeemUserParser();
			obj.user = (EyeemUser) modelParser.parse(newsItem.getJSONObject("user"));
		}
		
		if(newsItem.has("comment")){
			modelParser = new EyeemCommentParser();
			try{
				obj.comment = (EyeemComment) modelParser.parse(newsItem.getJSONObject("comment"));
			}catch(JSONException e){
				
			}
		}
		
		if(newsItem.has("album")){
			modelParser = new EyeemAlbumParser();
			try{
				obj.album = (EyeemAlbum) modelParser.parse(newsItem.getJSONObject("album"));
			}catch (JSONException e) {
				//backend bug do nothing
			}
			
		}
		
		if(newsItem.has("photo")){
			modelParser = new EyeemPhotoParser();
			obj.photo = (EyeemPhoto) modelParser.parse(newsItem.getJSONObject("photo"));
		}
		
		if(newsItem.has("message")){
			obj.message = (newsItem.getString("message"));
		}
		if(newsItem.has("title")){
			obj.title = (newsItem.getString("title"));
		}		
		
		if(newsItem.has("url")){
			obj.url = (newsItem.getString("url"));
		}
		
		if (newsItem.has("id")) {
			obj.newsId = newsItem.getInt("id");
		}
		if (newsItem.has("thumbUrl")) {
			obj.thumbUrl = (newsItem.getString("thumbUrl"));
		}
		if (newsItem.has("itemType")) {
			obj.itemType = (newsItem.getString("itemType"));
		}
		if (newsItem.has("newsType")) {
			obj.newsType = (newsItem.getString("newsType"));
		}
		
		if (newsItem.has("seen")) {
			obj.seen = (newsItem.getBoolean("seen"));
		}
		if(newsItem.has("updated")){
			obj.updatedString = newsItem.getString("updated");
			obj.updated = DateToMilli(obj.updatedString);
		}
	    return obj;
	}
	public ArrayList<EyeemNewsItem> parseNews(JSONObject json) throws JSONException{
		ArrayList<EyeemNewsItem> newsList = new ArrayList<EyeemNewsItem>();
		JSONArray news;
		if(json.has("news")){
			json = json.getJSONObject("news");
		} if(json.has("items")){
			news = json.getJSONArray("items");
			for(int i=0;i<news.length();i++){
				newsList.add(parse(news.getJSONObject(i)));
			}
		}
		return newsList;
	}
	
}