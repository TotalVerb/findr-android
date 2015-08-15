package com.example.findr;

import org.javalite.http.Get;
import org.javalite.http.Http;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Ian on 2015-08-15.
 */
public class Event {
    public String hostName, name, contact, location, description;
    public String[] tags, qualifications;
    public Time[] times;
    public double[] coordinates;
    public Policy policy;

    public void getEvents() throws JSONException {
        Get get = Http.get("https://afternoon-castle-4785.herokuapp.com/events");
        JSONArray arr = new JSONArray(get.text());
        for (int i = 0; i < arr.length(); i++){

        }
    }
}

enum Policy {
    DROP_IN, ARRIVE_AT_START
}

