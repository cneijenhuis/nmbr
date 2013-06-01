package com.baseapp.eyeem.androidsdk.models;

import java.io.Serializable;

public class EyeemTopic implements EyeemModelInterface, Serializable{
	private static final long serialVersionUID = 7526471155622776168L;

	public String name="";
	public boolean noTopic = false;
	public String topicId=null;
	public int totalPhotos=0;
	public int totalContributors=0;

	//timestamp for checking staleness!
	public long updated;

	public EyeemTopic() {
	}
	
	public EyeemTopic(String topicName) {
		name = topicName;
	}
	
	public EyeemTopic(String topicId, String name, int totalPhotos) {
		this.name = name;
		this.topicId = topicId;
		this.totalPhotos = totalPhotos;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
