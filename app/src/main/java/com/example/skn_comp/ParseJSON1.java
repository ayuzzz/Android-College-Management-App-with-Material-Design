package com.example.skn_comp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSON1 {
    public static String[] firsts;
   // public static String[] seconds;
   // public static String[] thirds;
   // public static String[] fourths;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_FIRST = "first";
   // public static final String KEY_SECOND = "second";
   // public static final String KEY_THIRD = "third";
   // public static final String KEY_FOURTH = "fourth";

    private JSONArray users = null;

    private String json;

    public ParseJSON1(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            firsts = new String[users.length()];
            //seconds = new String[users.length()];
            //thirds = new String[users.length()];
            //fourths = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                firsts[i] = jo.getString(KEY_FIRST);
                //seconds[i] = jo.getString(KEY_SECOND);
                //thirds[i] = jo.getString(KEY_THIRD);
                //fourths[i] = jo.getString(KEY_FOURTH);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}