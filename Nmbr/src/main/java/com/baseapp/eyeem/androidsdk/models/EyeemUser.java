package com.baseapp.eyeem.androidsdk.models;

import java.io.Serializable;

public class EyeemUser implements EyeemModelInterface, Serializable{
	private static final long serialVersionUID = 7526471155622776147L;

	public int totalFollowers, totalFriends, totalPhotos,totalNews;
	public String fullname="";
	public String nickname="";
	public String thumbUrl="";
	public String photofilename="";
	public String photoUrl="";
	public String description="";
	public int userId;
	public boolean simple=true;
	public int	totalPhotosContributed=-1;
	
	//timestamp for checking staleness!
	public long lastUpdated;

	public EyeemUser() {
	}
	
	public EyeemUser(int userId, String fullname, String thumbUrl) {
		this.fullname = fullname;
		this.userId = userId;
		this.thumbUrl = thumbUrl;
	}
	
	@Override
	public boolean equals(Object user){
		if(!(user instanceof EyeemUser))
			return false;
		if(this.userId == ((EyeemUser)user).userId)
			return true;
		return false;
	}
	
	public int hashCode(){
		return userId;
	}
	
}
