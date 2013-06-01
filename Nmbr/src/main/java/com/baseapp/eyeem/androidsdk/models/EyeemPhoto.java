package com.baseapp.eyeem.androidsdk.models;


import java.io.Serializable;
import java.util.Vector;

public class EyeemPhoto implements EyeemModelInterface, Serializable{
	private static final long serialVersionUID = 7526471155622776155L;
	
	public int photoId;
	public int width;
	public int height;
	public EyeemUser user;
	public int totalLikes;
	public int totalComments;
	public String thumbUrl;
	public String photoUrl;
	public String filename;
	public String caption;
	public String title;
	public Vector<EyeemUser> likers;
	public Vector<EyeemComment> comments;
	public Vector<EyeemAlbum> albums;
	
	public String updatedString;
	public long updated;
	
	public EyeemPhoto(){
		likers = new Vector<EyeemUser>();
		comments = new Vector<EyeemComment>();
		albums = new Vector<EyeemAlbum>();
	}
	
	public EyeemPhoto(int photoId, String thumbUrl, String photoUrl, int width, int height){
		this.photoId = photoId;
		this.thumbUrl = thumbUrl;
		this.photoUrl = photoUrl;
		this.width = width;
		this.height = height;
		
		likers = new Vector<EyeemUser>();
		comments = new Vector<EyeemComment>();
		albums = new Vector<EyeemAlbum>();
	}
	


	public float getAspectRatio() {
		return (float)width/(float)height;
	}
	

	
}
