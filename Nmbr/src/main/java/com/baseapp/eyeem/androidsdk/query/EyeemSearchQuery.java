package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class EyeemSearchQuery extends EyeEmAbstractQuery {

	public boolean users;
	
	private boolean usersFlag;
	
	public void setUsers(boolean users){
		this.users = users;
		usersFlag = true;
	}
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_SEARCH;
		ArrayList<NameValuePair> parameters = super.transformQuery();
		
		if(usersFlag){
			parameters.add(new BasicNameValuePair("users", String.valueOf(users)));
		}
		
		return parameters;
	}
}
