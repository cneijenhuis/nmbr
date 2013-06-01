package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



public class EyeemPhotosQuery extends EyeEmAbstractQuery {
	public enum EESpecificPhotosEndpoint{
	    EEspecificPhotosEndpointDiscover,
	    EEspecificPhotosEndpointTopics,
	    EEspecificPhotosEndpointShare,
	    EEspecificPhotosEndpointHide,
	    EEspecificPhotosEndpointLikers,
	    EEspecificPhotosEndpointComments,
	    EEspecificPhotosEndpointAlbums,
	    EEspecificPhotosEndpointFlags,
	    EEspecificPhotosEndpointBgImages,
	    EEspecificPhotosEndpointNothing
	};
	public EESpecificPhotosEndpoint specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointNothing;
	public boolean comments;
	public boolean likers;
	public int commentNumber = -1;
	public int likerNumber = -1;
	public int albumId = -1;
	public String caption;
	public String title;
	public String offense;
	public boolean hide;
	
	
	private boolean commentsFlag;
	private boolean likersFlag;
	private boolean hideFlag;
	
	
	public void setComments(boolean comments){
		this.comments = comments;
		commentsFlag = true;
	}
	public void setLikers(boolean likers){
		this.likers = likers;
		likersFlag = true;
	}
	public void setHide(boolean hide){
		this.hide = hide;
		hideFlag = true;
	}
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_PHOTOS;
		if(specificEndpoint != EESpecificPhotosEndpoint.EEspecificPhotosEndpointNothing){
			switch(specificEndpoint){
				case EEspecificPhotosEndpointAlbums:
					specificEndpointString = URL_ALBUMS;
					break;
				case EEspecificPhotosEndpointBgImages:
					specificEndpointString = URL_PHOTOS_BGIMAGES;
					break;
				case EEspecificPhotosEndpointComments:
					specificEndpointString = URL_COMMENTS;
					break;
				case EEspecificPhotosEndpointDiscover:
					specificEndpointString = URL_DISCOVER;
					break;
				case EEspecificPhotosEndpointFlags:
					specificEndpointString = URL_FLAG;
					break;
				case EEspecificPhotosEndpointHide:
					specificEndpointString = URL_HIDE;
					break;
				case EEspecificPhotosEndpointLikers:
					specificEndpointString = URL_LIKERS;
					break;
				case EEspecificPhotosEndpointShare:
					specificEndpointString = URL_SHARE;
					break;
				case EEspecificPhotosEndpointTopics:
					specificEndpointString = URL_TOPICS;
					break;
				default:
					break;
					
			}
		}
		 
		ArrayList<NameValuePair> parameters = super.transformQuery();
		
		if(commentsFlag){
			parameters.add(new BasicNameValuePair("comments", String.valueOf(comments)));
		}
		if(likersFlag){
			parameters.add(new BasicNameValuePair("likers", String.valueOf(likers)));			
		}
		if(commentNumber >= 0){
			parameters.add(new BasicNameValuePair("commentNumber", String.valueOf(commentNumber)));			
		}
		if(likerNumber >= 0){
			parameters.add(new BasicNameValuePair("likerNumber", String.valueOf(likerNumber)));			
		}
		if(albumId >= 0){
			parameters.add(new BasicNameValuePair("albumId", String.valueOf(albumId)));
		}
		if(caption != null){
			parameters.add(new BasicNameValuePair("caption", caption));
		}
		if(title != null){
			parameters.add(new BasicNameValuePair("title", title));
		}
		if(offense != null){
			parameters.add(new BasicNameValuePair("offense", offense));
		}
		if(hideFlag){
			parameters.add(new BasicNameValuePair("hide", String.valueOf(hide)));
		}
		return parameters;
	}

}
