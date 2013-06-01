package com.baseapp.eyeem.androidsdk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EyeemComment implements EyeemModelInterface, Serializable{
	private static final long serialVersionUID = 7526471155622776150L;

	public String message="";
	public String extendedMessage="";
	public EyeemUser author;
	public List<EyeemUser> mentionedUsers;
	public int commentId;
	public int photoId;

	//timestamp for checking staleness!
	public long updated;

	public EyeemComment() {
	}
	
	public EyeemComment(int commentId, EyeemUser author, String message){
		this.commentId = commentId;
		this.author = author;
		this.message = message;
	}
	
	//Parses a vector of photos and returns a distinct list of all mentioned users and commenters.
	public static ArrayList<EyeemUser> getPhotoMentionedUsers(EyeemPhoto mPhoto){
		
		ArrayList<EyeemUser> mentionedUsers = new ArrayList<EyeemUser>();
		for(EyeemComment c: mPhoto.comments){
			if(!mentionedUsers.contains(c.author)){
				mentionedUsers.add(c.author);
			}
			if(c.mentionedUsers !=null){
				for(EyeemUser u: c.mentionedUsers){
					if(!mentionedUsers.contains(u))
						mentionedUsers.add(u);
				}
			}
		}
		
		if(!mentionedUsers.contains(mPhoto.user))
			mentionedUsers.add(mPhoto.user);
		
		return mentionedUsers;
	}
}
