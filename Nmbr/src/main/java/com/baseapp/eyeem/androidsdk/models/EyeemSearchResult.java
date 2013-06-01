package com.baseapp.eyeem.androidsdk.models;


import java.io.Serializable;
import java.util.Vector;


public class EyeemSearchResult implements Serializable,EyeemModelInterface {
	private static final long serialVersionUID = 7526471155622776140L;
	public String searchString;
	public int totalUsers;
	public int totalAlbums;
	public Vector<EyeemAlbum> albums;
	public Vector<EyeemUser> users;
	
	public EyeemSearchResult() {
		albums = new Vector<EyeemAlbum>();
		users = new Vector<EyeemUser>();
	}
	

}
