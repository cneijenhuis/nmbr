package com.baseapp.eyeem.androidsdk.api;



import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONException;
import org.json.JSONObject;




import com.baseapp.eyeem.androidsdk.errors.EyeemException;
import com.baseapp.eyeem.androidsdk.errors.InvalidRequestException;
import com.baseapp.eyeem.androidsdk.errors.MissingParamException;
import com.baseapp.eyeem.androidsdk.errors.ResourceNotFoundException;
import com.baseapp.eyeem.androidsdk.errors.ServerErrorException;
import com.baseapp.eyeem.androidsdk.models.EyeemPagination;


public class ApiHelper {

	/**
	 * Helper function: checks the response code and throws an appropriate error (with an optional server error message)
	 */
	public static void checkResponse( RestClient client) throws EyeemException, InvalidRequestException, MissingParamException,
	ResourceNotFoundException, ServerErrorException{

		

		  
		  if ((client.getResponseCode()/100) == 2) //  we got a 2XX result everything is fine -> we are done
			  return;
		  

		  String response_msg=null;
		  
		  try {
			  response_msg=new JSONObject(client.getResponse()).getString("message");
		  } catch (Exception e) {
			  // we cannot parse the message and will output a generic error later - not much we can do here
		  }
		  
		  switch (client.getResponseCode() ) {
	    
	       case 400: // Bad Request
	    	   if (response_msg==null)
	    		   throw new EyeemException("error"); // generic error msg as we have no better
	    	   else
	    		   throw new EyeemException(response_msg);
	    	   
	       case 401: // Unauthorized
				//TODO run the actual logout?
	            throw new EyeemException("You have been logged out by the server.");
	       
	       case 403: // Forbidden
	    	   throw new EyeemException("You cannot do this at the moment.");
	    	   
	       case 404: // Not Found
	    	   ResourceNotFoundException e=new ResourceNotFoundException("Content not found.");
	           throw e;
	
	       case 500: // Internal Server Error
	           throw new ServerErrorException("Something went wrong, we will look into it right away!");
	
	       default:
	           throw new EyeemException(client.getResponseCode()+" error");
	   }
	}
	
	
	public final static int REST_RETRIES=3;
	

	
	public static JSONObject doRestWitJSONResult(RestClient client,HttpUriRequest request) 
			throws EyeemException {
		
		
		int try_cnt=0;
		boolean done=false;
		JSONObject res=null;
		
		while (!done) try {

			try_cnt++;
			

			client.executeRequest(request);		

			if ( client.getResponse() == null) 
				throw new EyeemException("something went wrong  !");
					
			checkResponse(client);
			if((client.getResponseCode()/100) != 2)
				break;
			if (res==null){
				res=new JSONObject( client.getResponse());
				break;
			}

				
			
			
			done=true;
		} catch (EyeemException e) {
			if (try_cnt>REST_RETRIES) 
				throw e;// we give up retrying
			else
				try {
					Thread.sleep(100*(try_cnt-1)); // sleeping *could* help
				} catch (InterruptedException e1) { } // no need to handle this
		} catch (JSONException e) {
			if (try_cnt>REST_RETRIES) 
				throw new EyeemException("Problem with the JSON we got from the server");
		} catch (OutOfMemoryError e) {
		   throw new EyeemException("Not enough memory to process request");
		}
		
		
	
   

		return res;
	}
	
	

	

	
	
	public static void addOffesetAndLimit(RestClient client, EyeemPagination pagination) {
		client.AddParam("offset",""+pagination.offset);
		client.AddParam("limit",""+pagination.limit);
	}
	

	
}