package com.baseapp.eyeem.androidsdk.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;




import com.baseapp.eyeem.androidsdk.parsers.json.*;
import com.baseapp.eyeem.androidsdk.errors.EyeemException;
import com.baseapp.eyeem.androidsdk.models.EyeemNewsPagination;
import com.baseapp.eyeem.androidsdk.models.EyeemPagination;


public class EyeEmAPI {

	public String clientId;
	public String accessToken;
	public String redirectURI;
	

	public final static String baseURL = "https://www.eyeem.com/api/v2";
	public final static String thumbURL = "http://www.eyeem.com/thumb";

	public EyeEmAPI(String clientId){
		this.clientId = clientId;
	}
	public EyeEmAPI(String clientId, String redirectURI){
		this(clientId);
		this.redirectURI = redirectURI;
		
	}
	public EyeEmAPI(String clientId, String redirectURI, String accessToken){
		this(clientId);
		this.accessToken = accessToken;
		this.redirectURI = redirectURI;

		
	}

	public boolean isOAuthValid(){
		return (accessToken != null);
	}
	public HashMap<String, Object> getRequest( ArrayList <NameValuePair> parameters) throws EyeemException, JSONException{
		
		RestClient client = new RestClient(parameters, baseURL);
		if(isOAuthValid()){
			client.AddParam("access_token", accessToken);
		}else {
			client.AddParam("client_id", clientId);			
		}
		HttpGet request = new HttpGet(client.getURLWithParams());

		HashMap<String, Object> a = sortResponse(ApiHelper.doRestWitJSONResult(client, request));
		return a;
		

	}

	public HashMap<String, Object> getRequest( ArrayList <NameValuePair> parameters, EyeemPagination pagination) throws EyeemException, JSONException{
		
		RestClient client = new RestClient(parameters, baseURL);
		ApiHelper.addOffesetAndLimit(client, pagination);
		if(isOAuthValid()){
			client.AddParam("access_token", accessToken);
		}else {
			client.AddParam("client_id", clientId);			
		}
		HttpGet request = new HttpGet(client.getURLWithParams());

		HashMap<String, Object> a = sortResponse(ApiHelper.doRestWitJSONResult(client, request));

		return a;
		

	}
	public HashMap<String, Object> putRequest(ArrayList <NameValuePair>  parameters) throws JSONException, EyeemException, UnsupportedEncodingException{
		RestClient client = new RestClient(parameters, baseURL);
		if(isOAuthValid()){
			client.AddParam("access_token", accessToken);
		}else {
			client.AddParam("client_id", clientId);			
		}
		
		HttpPut request = new HttpPut(client.getURLWithParams());
		if (!client.getParams().isEmpty()) {
			request.setEntity(new UrlEncodedFormEntity(client.getParams(), HTTP.UTF_8));
		}
		HashMap<String, Object> a = sortResponse(ApiHelper.doRestWitJSONResult(client, request));
		return a;
	}
	public HashMap<String, Object> postRequest(ArrayList <NameValuePair>  parameters) throws JSONException, EyeemException, UnsupportedEncodingException{
		RestClient client = new RestClient(parameters, baseURL);
		if(isOAuthValid()){
			client.AddParam("access_token", accessToken);
		}else {
			client.AddParam("client_id", clientId);			
		}
		HttpPost request = new HttpPost(client.getURLWithParams());
		if (!client.getParams().isEmpty()) {
			request.setEntity(new UrlEncodedFormEntity(client.getParams(), HTTP.UTF_8));
		}
		HashMap<String, Object> a = sortResponse(ApiHelper.doRestWitJSONResult(client, request));
		return a;
	}
	
	public HashMap<String, Object> sortResponse (JSONObject json) throws JSONException {
		HashMap<String, Object> newResponse = new HashMap<String, Object>();
		@SuppressWarnings( "unchecked" )
		Iterator<String> keys = json.keys();
	    while ( keys.hasNext() ) {
	    	String nextKey = keys.next();
	    	

			if(nextKey.equals("likedAlbums") || nextKey.equals("feedAlbums") || nextKey.equals("albums")){

				EyeemAlbumParser parser = new EyeemAlbumParser();
				newResponse.put("albums",parser.parseAlbums(json));
				JSONObject albums = (JSONObject) json.get(nextKey);

				if(albums.has("offset") && albums.has("limit") && albums.has("total")){
					newResponse.put("paginationAlbums", new  EyeemPagination(albums.getInt("offset"), albums.getInt("limit"), albums.getInt("total")));
				}
				
			} else if(nextKey.equals("album")){

				EyeemAlbumParser parser = new EyeemAlbumParser();
				newResponse.put("album",parser.parse(json));
			} else if(nextKey.equals("photos") || nextKey.equals("friendsPhotos")){
				System.out.println(nextKey);

				EyeemPhotoParser parser = new EyeemPhotoParser();
				newResponse.put("photos",parser.parsePhotos(json));
				JSONObject photos = (JSONObject) json.get(nextKey);
				if(photos.has("offset") && photos.has("limit") && photos.has("total")){
					newResponse.put("paginationPhotos", new  EyeemPagination(photos.getInt("offset"), photos.getInt("limit"), photos.getInt("total")));
				}

			} else if(nextKey.equals("photo")){
				EyeemPhotoParser parser = new EyeemPhotoParser();
				newResponse.put("photo",parser.parse(json));
			} else if(nextKey.equals("comments")){
				EyeemCommentParser parser = new EyeemCommentParser();
				newResponse.put("comments",parser.parseComments(json));
				JSONObject comments = (JSONObject) json.get(nextKey);
				if(comments.has("offset") && comments.has("limit") && comments.has("total")){
					newResponse.put("paginationUsers", new  EyeemPagination(comments.getInt("offset"), comments.getInt("limit"), comments.getInt("total")));
				}
			} else if(nextKey.equals("comment")){
				EyeemCommentParser parser = new EyeemCommentParser();
				newResponse.put("comment",parser.parse(json));
				
			} else if(nextKey.equals("topics")){
				EyeemTopicParser parser = new EyeemTopicParser();
				newResponse.put("topics",parser.parseTopics(json));
				
			} else if( nextKey.equals("users") || nextKey.equals("friends") || nextKey.equals("followers") || nextKey.equals("likers") || nextKey.equals("contributors") ){
				EyeemUserParser parser = new EyeemUserParser();
				newResponse.put("users",parser.parseUsers(json));
				JSONObject users = (JSONObject) json.get(nextKey);
				if(users.has("offset") && users.has("limit") && users.has("total")){
					newResponse.put("paginationUsers", new  EyeemPagination(users.getInt("offset"), users.getInt("limit"), users.getInt("total")));
				}
			} else if(nextKey.equals("user")){
				EyeemUserParser parser = new EyeemUserParser();
				newResponse.put("user",parser.parse(json));
			} else if(nextKey.equals("news")){
				EyeemNewsParser parser = new EyeemNewsParser();
				newResponse.put("news",parser.parseNews(json));
				JSONObject news = (JSONObject) json.get(nextKey);
				if(news.has("offset") && news.has("limit") && news.has("total") && news.has("unseen")){
					newResponse.put("paginationNews", new  EyeemNewsPagination(news.getInt("offset"), news.getInt("limit"), news.getInt("total"), news.getInt("unseen")));
				}
				 
			} 
			
		}

		return newResponse;
	}
	
	
	private String extractString (String  string)
	{
	    
	    String[] array = string.split("/");
	    
	    return array[array.length-1];
	    
	}
	
	public String urlFromPathForPhotoWithWidthAndHeight(String urlPath, int width, int height){
		if(urlPath == null || !urlPath.startsWith(thumbURL) || height <= 0 || width <= 0){
			return null;
		}
		urlPath = extractString( urlPath);
		
		return thumbURL+"/"+width+"/"+height+"/"+urlPath;
		
	}
	public String urlFromPathForPhotoWithWidth(String urlPath,  int width){
		if(urlPath == null || !urlPath.startsWith(thumbURL) || width <= 0){
			return null;
		}
		urlPath = extractString( urlPath);
		
		return thumbURL+"/w/"+width+"/"+urlPath;
	}
	public String urlFromPathForPhotoWithHeight(String urlPath, int height){
		if(urlPath == null || !urlPath.startsWith(thumbURL) || height <= 0){
			return null;
		}
		urlPath = extractString( urlPath);
		
		return thumbURL+"/h/"+height+"/"+urlPath;
		
	}
	public String urlFromPathForPhotoWithSquareSize(String urlPath, int imageSize){
		if(urlPath == null || !urlPath.startsWith(thumbURL) || imageSize <= 0){
			return null;
		}
		urlPath = extractString( urlPath);
		
		return thumbURL+"/sq/"+imageSize+"/"+urlPath;
		
	}
	public String urlFromFilenameWithWidthAndHeight(String filename, int width, int height){
		return thumbURL+"/"+width+"/"+height+"/"+filename;

	}
	public String urlFromFilenameWithWidth(String filename, int width){
		return thumbURL+"/w/"+width+"/"+filename;

	}
	public String urlFromFilenameWithHeight(String filename, int height){
		return thumbURL+"/h/"+height+"/"+filename;

	}
	public String urlFromFilenameWithSquareSize(String filename, int imageSize){
		return thumbURL+"/sq/"+imageSize+"/"+filename;

	}
}
