package com.baseapp.eyeem.androidsdk.models;


import java.io.Serializable;
import java.util.List;


public class EyeemNewsItem implements Serializable,EyeemModelInterface {
	private static final long serialVersionUID = 7526471155622776140L;
	
	public String newsType, itemType, message, title, thumbUrl,url;
	public long updated;
	public String updatedString;
	public boolean seen = false;
	public int newsId = -1;
	public int[] ids;
	
	public EyeemUser user = null;
	public EyeemPhoto photo = null;
	public EyeemAlbum album = null;
	public EyeemComment comment = null;
	
	public boolean hasAggregation = false;
	public List<?> aggregationList = null;
	public int aggregationTotal;
	public String aggregationType;
	

}
