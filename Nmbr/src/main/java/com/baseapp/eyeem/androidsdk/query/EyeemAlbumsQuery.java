package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class EyeemAlbumsQuery extends EyeEmAbstractQuery {

	public enum EESpecificAlbumsEndpoint {
	    EEspecificAlbumsEndpointPhotos,
	    EEspecificAlbumsEndpointShare,
	    EEspecificAlbumsEndpointHide,
	    EEspecificAlbumsEndpointLikers,
	    EEspecificAlbumsEndpointRecommended,
	    EEspecificAlbumsEndpointContributors,
	    EEspecificAlbumsEndpointView,
	    EEspecificAlbumsEndpointNothing
	};
	public EESpecificAlbumsEndpoint specificEndpoint = EESpecificAlbumsEndpoint.EEspecificAlbumsEndpointNothing;
	public boolean trending;
	public String geoSearch;
	public String foursquareId;
	public int minPhotos = -1;
	public double latitude = 500.;
	public double longitude = 500.;
	public boolean photos;
	public int photoNumber = -1;
	public boolean photoDetails;
	public boolean contributors;
	
	
	
	private boolean trendingFlag = false;
	private boolean photosFlag = false;
	private boolean photoDetailsFlag = false;
	private boolean contributorsFlag = false; 
	
	public void setTrending(boolean trending){
		this.trending = trending;
		trendingFlag = true;
	}
	public void setPhotos(boolean photos){
		this.photos = photos;
		photosFlag = true;
	}
	public void setPhotoDetails(boolean photoDetails){
		this.photoDetails = photoDetails;
		photoDetailsFlag = true;
	}
	public void setContributors(boolean contributors){
		this.contributors = contributors;
		contributorsFlag = true;
	}
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_ALBUMS;
		if(specificEndpoint != EESpecificAlbumsEndpoint.EEspecificAlbumsEndpointNothing){
			switch(specificEndpoint){
				case EEspecificAlbumsEndpointPhotos:
					specificEndpointString = URL_PHOTOS;
					break;
				case EEspecificAlbumsEndpointContributors:
					specificEndpointString = URL_CONTRIBUTORS;
					break;
				case EEspecificAlbumsEndpointHide:
					specificEndpointString = URL_HIDE;
					break;
				case EEspecificAlbumsEndpointLikers:
					specificEndpointString = URL_LIKERS;
					break;
				case EEspecificAlbumsEndpointRecommended:
					specificEndpointString = URL_RECOMMENDED;
					break;
				case EEspecificAlbumsEndpointShare:
					specificEndpointString = URL_SHARE;
					break;
				case EEspecificAlbumsEndpointView:
					specificEndpointString = URL_VIEW;
					break;
				default:
					break;
					
			}
		}
		
		ArrayList<NameValuePair> parameters = super.transformQuery();
		if (trendingFlag){
			parameters.add(new BasicNameValuePair("trending", String.valueOf(trending)));
		}
		if (geoSearch != null){
			parameters.add(new BasicNameValuePair("geoSearch", geoSearch));
		}
		if (foursquareId != null){
			parameters.add(new BasicNameValuePair("foursquareId", foursquareId));			
		}
		if (minPhotos > 0){
			parameters.add(new BasicNameValuePair("minPhotos", String.valueOf(minPhotos)));			
		}
		if (latitude >= -90.0 && latitude <= 90.0 && longitude >= -180.0 && longitude <= 180.0){
			parameters.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));			
			parameters.add(new BasicNameValuePair("longitude", String.valueOf(longitude)));			
		
		}
		if (photosFlag){
			parameters.add(new BasicNameValuePair("photos", String.valueOf(photos)));						
		}
		if (photoNumber > 0){
			parameters.add(new BasicNameValuePair("photoNumber", String.valueOf(photoNumber)));			
		}
		if (photoDetailsFlag){
			parameters.add(new BasicNameValuePair("photoDetails", String.valueOf(photoDetails)));					
		}
		if (contributorsFlag){
			parameters.add(new BasicNameValuePair("contributors", String.valueOf(contributors)));					
		}
		
		return parameters;
	}
}
