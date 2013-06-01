package com.baseapp.eyeem.androidsdk.api;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class EyeemConnect extends Activity {
	protected EyeEmAPI api;
	
	public void setEyeEmAPI (EyeEmAPI api){
		this.api = api;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	protected void loginToEyeEm() {
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(authorizationURL()));
	     startActivity(launchBrowser);

	}
	public String authorizationURL(){
		try {
			return "http://www.eyeem.com/oauth/authorize?response_type=code" + 
				    "&client_id=" + api.clientId + "&redirect_uri=" + URLEncoder.encode(api.redirectURI, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
		
	}
	 
}
