package com.baseapp.eyeem.androidsdk.models;

public class EyeemPagination {
	public int offset;
	public int limit;
	public int total;
	
	public EyeemPagination(int offset, int limit){
		this.offset = offset;
		this.limit = limit;
		
	}
	
	public EyeemPagination(int offset, int limit, int total){
		this( offset,  limit);
		this.total = total; 
	}
	public void moveForward(){
		offset += limit;
	}

}
