package com.baseapp.eyeem.androidsdk.models;

public class EyeemNewsPagination extends EyeemPagination {
	public int unseen;
	public EyeemNewsPagination(int offset, int limit, int total, int unseen) {
		super(offset, limit, total);
		this.unseen = unseen;
		// TODO Auto-generated constructor stub
	}

}
