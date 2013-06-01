package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class EyeemVenueQuery extends EyeEmAbstractQuery {
	public enum EESpecificVenuesEndpoint {
	    EEspecificVenuesEndpointFoursqareToken,
	    EEspecificVenuesEndpointNothing
	};
	public EESpecificVenuesEndpoint specificEndpoint = EESpecificVenuesEndpoint.EEspecificVenuesEndpointNothing;
	public String service;
	public int venueId = -1;
	public HashMap<String,Object> location;
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_VENUES;
		if(specificEndpoint != EESpecificVenuesEndpoint.EEspecificVenuesEndpointNothing){
			switch(specificEndpoint){
				case EEspecificVenuesEndpointFoursqareToken:
					specificEndpointString = URL_FOURSQUARETOKEN;
					break;
				default:
					break;
					
			}
		}
		
		ArrayList<NameValuePair> parameters = super.transformQuery();
		if(service != null){
			parameters.add(new BasicNameValuePair("service", service));
		}
		if(venueId > 0){
			parameters.add(new BasicNameValuePair("venueId", String.valueOf(venueId)));
		}
		if(location != null){
			Enumeration<String> enumerate = Collections.enumeration(location.keySet());


		    while (enumerate.hasMoreElements()){
		    	String nextKey =  enumerate.nextElement();
		    	parameters.add(new BasicNameValuePair(nextKey, String.valueOf(location.get(nextKey))));
		    }
		}
		
		return parameters;
	}
}
