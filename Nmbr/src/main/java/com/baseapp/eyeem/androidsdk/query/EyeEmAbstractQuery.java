package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class EyeEmAbstractQuery {
	protected static final String URL_AUTH = "/auth";
	protected static final String URL_USERS = "/users";
	protected static final String URL_PHOTOS = "/photos";
	protected static final String URL_PHOTOS_POPULAR = "/popular";
	protected static final String URL_PHOTOS_BGIMAGES = "/bgImages";
	protected static final String URL_FLAG = "/flag";
	protected static final String URL_DISCOVER = "/discover";
	protected static final String URL_ALBUMS = "/albums";
	protected static final String URL_NEWS = "/news";
	protected static final String URL_PING = URL_AUTH + "/ping";
	protected static final String URL_PUSH = URL_AUTH + "/push";
	protected static final String URL_SEARCH = "/search";
	protected static final String URL_SHARE = "/share";	
	protected static final String URL_VENUES = "/venues";	
	protected static final String URL_FOURSQUARETOKEN = "/foursquareToken";	

	
	
	//Users
	protected static final String URL_USERS_LIKEDPHOTOS = "/likedPhotos";
	protected static final String URL_USERS_LIKEDALBUMS = "/likedAlbums";

	protected static final String URL_USERS_FOLLOWERS = "/followers";
	protected static final String URL_USERS_FRIENDS = "/friends";
	protected static final String URL_USERS_FRIENDS_PHOTOS = "/friendsPhotos";
	protected static final String URL_TOPICS = "/topics";
	protected static final String URL_USERS_NEWS_SETTINGS = "/newsSettings";
	protected static final String URL_FEED = "/feed";	

	
	//Contacts
	protected static final String URL_USERS_FRIENDS_SOCIAL_MEDIA = "/me/smContacts";
	protected static final String URL_USERS_SOCIAL_MEDIA = "/socialMedia/";
	protected static final String URL_USER_DISCOVER = "/me/discover";
	
	//Photos
	
	//Albums
	protected static final String URL_LIKERS = "/likers";
	protected static final String URL_CONTRIBUTORS = "/contributors";
	protected static final String URL_COMMENTS = "/comments";
	protected static final String URL_RECOMMENDED = "/recommended";
	protected static final String URL_MUTE = "/mute";
	protected static final String URL_HIDE = "/hide";
	protected static final String URL_VIEW = "/hide";
	
	
	
	protected String generalEndpoint;
	public int firstId = -1;
	public String firstIdString;
	public String specificEndpointString;
	public int secondId = -1;
	public String secondIdString;
	public String type;
	public String ids;
	public String q;
	public boolean onlyId;
	public boolean detailed;
	public boolean albums;
	public String message;
	public String services;
	public String name;
	
	
	
	//Flags
	
	protected boolean onlyIdFlag = false;
	protected boolean detailedFlag = false;
	protected boolean albumsFlag = false;
	
	public void setOnlyId (boolean onlyId){
		this.onlyId = onlyId;
		onlyIdFlag = true;
	}
	public void setDetailed (boolean detailed){
		this.detailed = detailed;
		detailedFlag = true;
	}
	public void setAlbums (boolean albums){
		this.albums = albums;
		albumsFlag = true;
	}
	
	
	public ArrayList<NameValuePair> transformQuery(){
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		String endpoint = generalEndpoint;
		if ((firstId > 0 || firstIdString != null) && specificEndpointString != URL_PHOTOS_BGIMAGES){
			if(firstIdString != null ){
				endpoint = endpoint + "/" + firstIdString;
			} else {
				endpoint = endpoint + "/" + String.valueOf(firstId);
			}
			if(specificEndpointString != null){
				endpoint += specificEndpointString;
				if (secondId > 0 || secondIdString != null){
					if(secondIdString != null){
						endpoint += "/" + secondIdString;
					} else {
						endpoint += "/" + String.valueOf(secondId);
					}
				}

			}
		} else if(specificEndpointString != null){
			endpoint += specificEndpointString;
			if (secondId > 0 || secondIdString != null){
				if(secondIdString != null){
					endpoint += "/" + secondIdString;
				} else {
					endpoint += "/" + String.valueOf(secondId);
				}
			}

		}
		parameters.add(new BasicNameValuePair("endpoint", endpoint));
		if (type != null){
			parameters.add(new BasicNameValuePair("type", type));
		}
		if (ids != null){
			parameters.add(new BasicNameValuePair("ids", ids));
		}
		if (q != null){
			parameters.add(new BasicNameValuePair("q", q));
		}
		if (services != null){
			parameters.add(new BasicNameValuePair("services", services));
		}
		if (onlyIdFlag){
			parameters.add(new BasicNameValuePair("onlyId", String.valueOf(onlyId)));
		}
		if (detailedFlag){
			parameters.add(new BasicNameValuePair("detailed", String.valueOf(detailed)));
		}
		if (albumsFlag){
			parameters.add(new BasicNameValuePair("albums", String.valueOf(albums)));
		}
		if (message != null){
			parameters.add(new BasicNameValuePair("message", message));
		}
		if (name != null){
			parameters.add(new BasicNameValuePair("name", name));
		}
		
		return parameters;
	}
}
