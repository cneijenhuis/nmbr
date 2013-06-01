package com.baseapp.eyeem.androidsdk.models;

import java.io.Serializable;
import java.util.ArrayList;

public class EyeemAlbum implements EyeemModelInterface, Serializable{
	private static final long serialVersionUID = 7526471155622776148L;

	public final static String SPECIALTYPE_NEARBY="nearby";
	public final static String SPECIALTYPE_RECOMMENDED="recommended";
	public final static String SPECIALTYPE_LIVE="live";
	
	public final static String TYPE_CITY="city";
	public final static String TYPE_VENUE="venue";
	public final static String TYPE_EVENT="event";
	public final static String TYPE_COUNTRY="country";
	public final static String TYPE_TAG="tag";
	
	public final static String SPECIALTYPE_JUST_UPLOADED="justUploaded";
	
	public String name="";
	public String thumbUrl="";
	public String filename="";
	public String albumId;
	public int totalPhotos, totalLikers, totalContributors;
	public ArrayList<EyeemPhoto> albumPhotos;
	public String type;
	public String specialType="";
	public String feedDescriptionText="";
	public String lat=null;
	public String lng=null;
	public boolean hidden;
	
	public EyeemAlbum cityAlbum;
	public EyeemAlbum countryAlbum;
	public String categoryName;
	
	public boolean simple=true;

	//timestamp for checking staleness!
	public long updated;

	public EyeemAlbum() {
	}
	
	public EyeemAlbum(String albumId, String name, String thumbUrl) {
		this.name = name;
		this.albumId = albumId;
		this.thumbUrl = thumbUrl;
	}
	
	


	
}
