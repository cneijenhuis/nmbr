package com.baseapp.eyeem.androidsdk.errors;

/*
 * Handles a lost/unavailable internet connection
 */
public class NoConnectionException extends EyeemException {

	public NoConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public NoConnectionException(){
		super("Cannot establish an internet connection. Please check your settings!");
	}

	private static final long serialVersionUID = 1L;

}
