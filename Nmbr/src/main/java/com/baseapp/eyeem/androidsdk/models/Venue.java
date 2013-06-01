package com.baseapp.eyeem.androidsdk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Venue implements Serializable{
	//7526471155622776148 is updated serialVersionUID from 04.07
	private static final long serialVersionUID = 7526471155622776148L;

	public HashMap<String, String> venueSpecs;
	public String venueName;
	public String venueId;
	public String venueServiceName="foursquare";
	public boolean isCity = false;
	public boolean noLocation = false;
	public boolean newLocation = false;
	public ArrayList<String> categories;
	public Venue() {
		venueSpecs = new HashMap<String, String>();
		categories = new ArrayList<String>();
	}
}