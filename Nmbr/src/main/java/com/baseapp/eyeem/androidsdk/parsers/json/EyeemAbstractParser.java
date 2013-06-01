package com.baseapp.eyeem.androidsdk.parsers.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemModelInterface;

public abstract class EyeemAbstractParser<ModelType extends EyeemModelInterface> implements EyeemParserInterface<ModelType>{

    public abstract ModelType parse(JSONObject json) throws JSONException;
        
    public ArrayList<ModelType> parse(JSONArray array) throws JSONException {
        throw new JSONException("Unexpected JSONArray parse type encountered.");
    }
    
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",Locale.getDefault());

	public static long DateToMilli(String dateString){

			try {
				return simpleDateFormat.parse(dateString).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;



	}
}