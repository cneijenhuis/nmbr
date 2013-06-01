package com.baseapp.eyeem.androidsdk.parsers.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baseapp.eyeem.androidsdk.models.EyeemModelInterface;


public interface EyeemParserInterface<ModelType extends EyeemModelInterface> {

    public abstract ModelType parse(JSONObject json) throws JSONException;
    public ArrayList<ModelType> parse(JSONArray array) throws JSONException;
}
