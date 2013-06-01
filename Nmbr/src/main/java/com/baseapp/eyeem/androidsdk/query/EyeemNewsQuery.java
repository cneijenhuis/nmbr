package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class EyeemNewsQuery extends EyeEmAbstractQuery {

	public boolean aggregated;
	public boolean countUnseen;
	public boolean include_older;
	public String markAsRead;
	
	
	private boolean aggregatedFlag;
	private boolean countUnseenFlag;
	private boolean include_olderFlag;

	public void setAggregated(boolean aggregated){
		this.aggregated = aggregated;
		aggregatedFlag = true;
	}
	public void setCountUnseen(boolean countUnseen){
		this.countUnseen = countUnseen;
		countUnseenFlag = true;
	}
	public void setInclude_older(boolean include_older){
		this.include_older = include_older;
		include_olderFlag = true;
	}
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_NEWS;
		ArrayList<NameValuePair> parameters = super.transformQuery();
		if(aggregatedFlag){
			parameters.add(new BasicNameValuePair("aggregated", String.valueOf(aggregated)));
		}
		if(countUnseenFlag){
			parameters.add(new BasicNameValuePair("countUnseen", String.valueOf(countUnseen)));			
		}
		if(include_olderFlag){
			parameters.add(new BasicNameValuePair("include_older", String.valueOf(include_older)));
		}
		if(markAsRead != null){
			parameters.add(new BasicNameValuePair("markAsRead", String.valueOf(markAsRead)));
		}
		return parameters;
		
	}
}
