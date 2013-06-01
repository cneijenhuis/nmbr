package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class EyeemTopicsQuery extends EyeEmAbstractQuery {

	public String autoComplete;
	
	public ArrayList<NameValuePair> transformQuery(){
		
		generalEndpoint = URL_TOPICS;
		
		ArrayList<NameValuePair> parameters = super.transformQuery();
		
		if(autoComplete != null){
			parameters.add(new BasicNameValuePair("autoComplete", autoComplete));
		}
		

		return parameters;
	}
	
}
