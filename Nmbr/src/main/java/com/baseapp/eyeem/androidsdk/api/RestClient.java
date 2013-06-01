package com.baseapp.eyeem.androidsdk.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;




public class RestClient {
	
	public static final int HTTP_GET=1,HTTP_POST=2,HTTP_PUT=3,HTTP_DELETE=4;
	private ArrayList<NameValuePair> params;
	public ArrayList<NameValuePair> headers;

	private String url;

	private int responseCode;
	private String message;

	private String response;

	private int last_method=0;
	private boolean has_access_token=false;
	

	
	public String getResponse() {
		return response;
	}

	public String getErrorMessage() {
		return message;
	}

	public int getResponseCode() {
		return responseCode;
	}
	public RestClient(ArrayList<NameValuePair> params, String url) {
		this.url = url;
		this.params = params;
		headers = new ArrayList<NameValuePair>();
		AddHeader("Accept-Encoding", "gzip");
	}
	public RestClient() {

		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
		AddHeader("Accept-Encoding", "gzip");
	}
	public RestClient(String url) {
		this();
		this.url = url;

	}
	
	public RestClient(String url, String access_token) {
		this(url);
		AddHeader("Authorization", "Bearer "+access_token);
		has_access_token=true;
	}	
/*
	public RestClient(ApplicationController ac, String url) throws NoConnectionException{
		this(url);
		if(!ConnectionChecker.checkConnection(ac)){
			throw new NoConnectionException();
		}
	}	
	
	public RestClient(ApplicationController ac, String url, String access_token) throws NoConnectionException{
		this(url,access_token);
		if(!ConnectionChecker.checkConnection(ac)){
			throw new NoConnectionException();
		}
	}	
	*/
	public void AddParam(String name, String value) {
		params.add(new BasicNameValuePair(name, value));
	}
	
	public void RemoveParam(String name, String value) {
		params.remove(new BasicNameValuePair(name, value));
	}

	public void AddHeader(String name, String value) {
		headers.add(new BasicNameValuePair(name, value));
	}



	public void executeRequest(HttpUriRequest request) {
		for (NameValuePair h : headers) {
			request.addHeader(h.getName(), h.getValue());
		}

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse;

		try {
			httpResponse = httpClient.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();

			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				
				InputStream instream = entity.getContent();
				Header contentEncoding = httpResponse
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				response = convertStreamToString(instream);

				// Closing the input stream will trigger connection release
				instream.close();
			}			

		} catch (ClientProtocolException e) {
			httpClient.getConnectionManager().shutdown();
			e.printStackTrace();
		} catch (IOException e) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public String getURL() {
		return url;
	}
	public ArrayList<NameValuePair> getParams() {
		return params;
	}
	public String getURLWithParams() {
		return url + getCombinedParams();
	}
	
	public String getStringWithURLandHeaders() {
		String res=getURLWithParams() + " (Method: " + getLastMethodSting() + " Header: ";
		for (NameValuePair nvp:headers)
			res+=nvp.getName()+"=>"+nvp.getValue() + " ";
		
		return res+")";
	}
	
	public String getCombinedParams() {
		String combinedParams = "";
		String endpoint = "";
		if (!params.isEmpty()) {
			combinedParams += "?";
			for (NameValuePair p : params) {
				if(p.getName() == "endpoint"){
					endpoint = p.getValue();
				} else {
					String paramString = p.getName() + "=";	
					
					try {
						paramString+=URLEncoder.encode(p.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
					}
					
					if (combinedParams.length() > 1) {
						combinedParams += "&" + paramString;
					} else {
						combinedParams += paramString;
					}
				}
				
			}
		}
		return endpoint + combinedParams;
	}
	
	public String getLastMethodSting() {
		switch( last_method ) {
		case 0:
			return "NONE";
		case 1:
			return "GET";
		case 2:
			return "POST";
		case 3:
			return "PUT";
		case 4:
			return "DELETE";
		default:
			return "#FAIL"; 
		}
	}
	
	public boolean hasAccessToken() {
		return has_access_token;
	}
	
}